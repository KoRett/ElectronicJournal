package com.gajeks.electronicjournal.models

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.gajeks.electronicjournal.databinding.BaseScheduleFragmentBinding
import com.gajeks.electronicjournal.domain.models.selected_date.DateChangeObserver
import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
import com.gajeks.electronicjournal.presentation.tabs.view_pager.ScheduleDateViewPager
import java.util.*
import javax.inject.Inject

abstract class BaseScheduleFragment : BaseFragment(), DateChangeObserver {

    protected var viewGroup: ViewGroup? = null
    private var _baseBinding: BaseScheduleFragmentBinding? = null
    private val baseBinding get() = _baseBinding!!
    private var lastPosition = -1
    private var vpAdapter: ScheduleDateViewPager? = null
    private var cal: Calendar = Calendar.getInstance()
    private var currentWeek: Int? = null

    @Inject
    lateinit var selectedDate: SelectedDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cal.setState()
        selectedDate.addDateChangeObserver(this)
        selectedDate.selectedWeek = cal.getCurrentWeek() ?: (MAX_WEEK_NUMBER - 1)
        currentWeek = cal.getCurrentWeek()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _baseBinding = BaseScheduleFragmentBinding.bind(viewGroup!!)

        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpAdapter = ScheduleDateViewPager(
            fragment = this,
            currentWeek = currentWeek
        )

        baseBinding.vpDates.adapter = vpAdapter
        baseBinding.vpDates.setCurrentItem(
            selectedDate.selectedWeek ?: (MAX_WEEK_NUMBER - 1),
            false
        )

        if (lastPosition == -1) {
            lastPosition = baseBinding.vpDates.currentItem
            cal.add(Calendar.DAY_OF_MONTH, 7 * lastPosition)
            cal.add(Calendar.DAY_OF_MONTH, 3)
        }

        baseBinding.imBtBack.setOnClickListener {
            baseBinding.vpDates.setCurrentItem(baseBinding.vpDates.currentItem - 1, true)
        }

        baseBinding.imBtForward.setOnClickListener {
            baseBinding.vpDates.setCurrentItem(baseBinding.vpDates.currentItem + 1, true)
        }

        baseBinding.vpDates.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentWeek(position)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (lastPosition != -1)
            setCurrentWeek(baseBinding.vpDates.currentItem)
        else
            lastPosition = baseBinding.vpDates.currentItem
        onDateChange()
    }

    @SuppressLint("SetTextI18n")
    fun setCurrentWeek(position: Int) {
        cal.add(Calendar.DAY_OF_MONTH, 7 * (position - lastPosition))
        baseBinding.textDate.text =
            "${cal.getMonth()} ${cal.get(Calendar.YEAR)} г.\n" +
                    "Выбрана ${position + 1} неделя"
        lastPosition = position
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _baseBinding = null
        selectedDate.removeDateChangeObserver(this)
        vpAdapter = null
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

    companion object {
        const val MAX_WEEK_NUMBER = 17
    }
}