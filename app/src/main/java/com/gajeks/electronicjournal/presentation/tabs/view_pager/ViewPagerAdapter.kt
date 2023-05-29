package com.gajeks.electronicjournal.presentation.tabs.view_pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gajeks.electronicjournal.models.setState
import java.util.Calendar

class ViewPagerAdapter(
    val fragment: Fragment,
    private val currentWeek: Int
) : FragmentStateAdapter(fragment) {

    private var cal: Calendar = Calendar.getInstance()

    init {
        cal.setState()
    }

    override fun getItemCount(): Int = 17

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerItem()

        cal.add(Calendar.DAY_OF_MONTH, position * 7)

        val monday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val tuesday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val wednesday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val thursday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val friday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val saturday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val sunday = cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DAY_OF_MONTH, 1)

        cal.add(Calendar.DAY_OF_MONTH, -(position + 1) * 7)

        val currentWeekday = if (position == currentWeek) {
            Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        } else {
            -1
        }

        fragment.arguments = ViewPagerItemArgs(
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday,
            sunday,
            position,
            currentWeekday
        ).toBundle()

        return fragment
    }

}