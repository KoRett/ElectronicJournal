package com.gajeks.electronicjournal.presentation.tabs.view_pager

import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.ScheduleViewPagerItemBinding
import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
import com.gajeks.electronicjournal.domain.models.selected_date.WeekObserver
import java.util.Calendar
import javax.inject.Inject

class ScheduleViewPagerItem : Fragment(), WeekObserver {

    private var _binding: ScheduleViewPagerItemBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ScheduleViewPagerItemArgs>()
    private var imId: Int? = null
    private var imIndex: Int? = null

    @Inject
    lateinit var selectedDate: SelectedDate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleViewPagerItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textMonday.text = args.monday.toString()
        binding.textMonday.setOnClickListener { selectDate(Calendar.MONDAY) }

        binding.textTuesday.text = args.tuesday.toString()
        binding.textTuesday.setOnClickListener { selectDate(Calendar.TUESDAY) }

        binding.textWednesday.text = args.wednesday.toString()
        binding.textWednesday.setOnClickListener { selectDate(Calendar.WEDNESDAY) }

        binding.textThursday.text = args.thursday.toString()
        binding.textThursday.setOnClickListener { selectDate(Calendar.THURSDAY) }

        binding.textFriday.text = args.friday.toString()
        binding.textFriday.setOnClickListener { selectDate(Calendar.FRIDAY) }

        binding.textSaturday.text = args.saturday.toString()
        binding.textSaturday.setOnClickListener { selectDate(Calendar.SATURDAY) }

        binding.textSunday.text = args.sunday.toString()
        binding.textSunday.setOnClickListener { selectDate(Calendar.SUNDAY) }

        if (args.currentWeekday != -1) {
            val imCurrentDay = ImageView(context)
            imCurrentDay.setImageResource(R.drawable.im_current)
            imCurrentDay.layoutParams = getLayoutParams(args.currentWeekday)
            binding.viewPagerItem.addView(imCurrentDay, 0)
            imIndex = 1
        } else
            imIndex = 0

        if (selectedDate.selectedWeek == args.position) {
            selectDate(selectedDate.selectedWeekday!!)
        }
    }

    private fun selectDate(weekday: Int) {
        if (imId == null) {
            val imCurrentDay = ImageView(context)
            imId = View.generateViewId()
            imCurrentDay.setImageResource(R.drawable.im_selected)
            imCurrentDay.layoutParams = getLayoutParams(weekday)
            imCurrentDay.id = imId!!
            binding.viewPagerItem.addView(imCurrentDay, imIndex!!)
        } else {
            val imCurrentDay = binding.viewPagerItem[imIndex!!] as ImageView
            TransitionManager.beginDelayedTransition(
                binding.root,
                AutoTransition().setDuration(200)
            )
            imCurrentDay.layoutParams = getLayoutParams(weekday)
        }
        selectedDate.selectedWeek = args.position
        selectedDate.selectedWeekday = weekday
        selectedDate.weekObserver = this
    }

    private fun getLayoutParams(dayOfWeek: Int): ConstraintLayout.LayoutParams {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        layoutParams.topToTop = R.id.text_monday
        layoutParams.bottomToBottom = R.id.text_monday

        when (dayOfWeek) {
            Calendar.MONDAY -> {
                layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                layoutParams.endToStart = R.id.text_tuesday
            }
            Calendar.TUESDAY -> {
                layoutParams.startToEnd = R.id.text_monday
                layoutParams.endToStart = R.id.text_wednesday
            }
            Calendar.WEDNESDAY -> {
                layoutParams.startToEnd = R.id.text_tuesday
                layoutParams.endToStart = R.id.text_thursday
            }
            Calendar.THURSDAY -> {
                layoutParams.startToEnd = R.id.text_wednesday
                layoutParams.endToStart = R.id.text_friday
            }
            Calendar.FRIDAY -> {
                layoutParams.startToEnd = R.id.text_thursday
                layoutParams.endToStart = R.id.text_saturday
            }
            Calendar.SATURDAY -> {
                layoutParams.startToEnd = R.id.text_friday
                layoutParams.endToStart = R.id.text_sunday
            }
            Calendar.SUNDAY -> {
                layoutParams.startToEnd = R.id.text_saturday
                layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            }
            else -> throw IllegalArgumentException()
        }

        return layoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imId = null
        selectedDate.weekObserver = null
    }

    override fun onWeekSelection() {
        if (binding.viewPagerItem[imIndex!!].id == imId) {
            binding.viewPagerItem.removeViewAt(imIndex!!)
            imId = null
        }
    }

}