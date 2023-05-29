package com.gajeks.electronicjournal.presentation.tabs.student.schedule

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentStudentScheduleBinding
import com.gajeks.electronicjournal.domain.models.DateChangeObserver
import com.gajeks.electronicjournal.domain.models.SelectedDate
import com.gajeks.electronicjournal.models.*
import com.gajeks.electronicjournal.presentation.tabs.view_pager.ViewPagerAdapter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class StudentScheduleFragment : BaseFragment(), DateChangeObserver {

    private var _binding: FragmentStudentScheduleBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var selectedDate: SelectedDate

    @Inject
    lateinit var vmFactory: Provider<StudentScheduleViewModel.Factory>
    override val vm: StudentScheduleViewModel by viewModels { vmFactory.get() }
    private var lastPosition = -1
    private var vpAdapter: ViewPagerAdapter? = null

    private var cal: Calendar = Calendar.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cal.setState()
        selectedDate.selectedWeek = getCurrentWeek(cal.clone() as Calendar)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentScheduleBinding.inflate(inflater, container, false)

        vpAdapter = ViewPagerAdapter(
            fragment = this,
            currentWeek = selectedDate.selectedWeek ?: 0
        )

        selectedDate.addDateChangeObserver(this)

        binding.vpDates.adapter = vpAdapter
        binding.vpDates.setCurrentItem(selectedDate.selectedWeek ?: 0, false)
        if (lastPosition == -1) {
            lastPosition = binding.vpDates.currentItem
            cal.add(Calendar.DAY_OF_MONTH, 7 * lastPosition)
            cal.add(Calendar.DAY_OF_MONTH, 3)
        }

        binding.vpDates.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentWeek(position)
            }
        })

        binding.imBtBack.setOnClickListener {
            binding.vpDates.setCurrentItem(binding.vpDates.currentItem - 1, true)
        }

        binding.imBtForward.setOnClickListener {
            binding.vpDates.setCurrentItem(binding.vpDates.currentItem + 1, true)
        }

        vm.lessons.observe(viewLifecycleOwner) { lessons ->
            renderedSimpleResult(binding.clSchedule, lessons) {
                binding.lessonList.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = ScheduleAdapter(it)
                    edgeEffectFactory = BounceEdgeEffectFactory()
                }
            }
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (lastPosition != -1)
            setCurrentWeek(binding.vpDates.currentItem)
        else
            lastPosition = binding.vpDates.currentItem
        if (selectedDate.selectedWeek == null)
            selectedDate.selectedWeek = binding.vpDates.currentItem
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentWeek(position: Int) {
        cal.add(Calendar.DAY_OF_MONTH, 7 * (position - lastPosition))
        binding.textDate.text =
            "${getMonth(cal.get(Calendar.MONTH))} ${cal.get(Calendar.YEAR)} г.\n" +
                    "Выбрана ${position + 1} неделя"
        lastPosition = position
    }

    private fun getMonth(month: Int): String = when (month) {
        0 -> "Январь"
        1 -> "Февраль"
        2 -> "Март"
        3 -> "Апрель"
        4 -> "Май"
        5 -> "Июнь"
        6 -> "Июль"
        7 -> "Август"
        8 -> "Сентябрь"
        9 -> "Октябрь"
        10 -> "Ноябрь"
        11 -> "Декабрь"
        else -> throw IllegalArgumentException()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentWeek(cal: Calendar): Int? {
        val sdf = SimpleDateFormat("dd/MM/yyyy")

        val currentCalendar = Calendar.getInstance()

        val currentDate: Date = sdf.parse(
            "${currentCalendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${currentCalendar.get(Calendar.MONTH) + 1}/" +
                    "${currentCalendar.get(Calendar.YEAR)}"
        ) as Date

        val startDate: Date = sdf.parse(
            "${cal.get(Calendar.DAY_OF_MONTH)}/" +
                    "${cal.get(Calendar.MONTH) + 1}/" +
                    "${cal.get(Calendar.YEAR)}"
        ) as Date

        val diff = kotlin.math.abs(currentDate.time - startDate.time)
        val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

        return if (days <= 118) {
            (days / 7).toInt()
        } else {
            null
        }
    }

    override fun onDateChange() {
        cal.add(Calendar.DAY_OF_MONTH, -3)
        val weekday = selectedDate.selectedWeekday!! - 2
        cal.add(
            Calendar.DAY_OF_MONTH, if (weekday == -1) {
                6
            } else {
                weekday
            }
        )
        selectedDate.date = "${cal.get(Calendar.DAY_OF_MONTH)}/" +
                "${cal.get(Calendar.MONTH) + 1}/" +
                "${cal.get(Calendar.YEAR)}"
        cal.add(
            Calendar.DAY_OF_MONTH, if (weekday == -1) {
                -6
            } else {
                -weekday
            }
        )
        cal.add(Calendar.DAY_OF_MONTH, 3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        selectedDate.removeDateChangeObserver(this)
        vpAdapter = null
    }
}