package com.gajeks.electronicjournal.presentation.tabs.student

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentStudentTabsBinding
import com.gajeks.electronicjournal.domain.models.SelectedDate
import java.util.Calendar
import javax.inject.Inject

class StudentTabsFragment : Fragment() {

    private var _binding: FragmentStudentTabsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var selectedDate: SelectedDate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentTabsBinding.inflate(layoutInflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_news,
            R.id.tabs_student_schedule_graph,
            R.id.navigation_profile
        ).build()

        val navHost =
            childFragmentManager.findFragmentById(R.id.tabs_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        setupActionBarWithNavController(
            requireActivity() as AppCompatActivity,
            navController,
            appBarConfiguration
        )

        binding.navBottomView.setupWithNavController(navController)

        val cal = Calendar.getInstance()

        selectedDate.selectedWeekday = cal.get(Calendar.DAY_OF_WEEK)
        selectedDate.date = "${cal.get(Calendar.DAY_OF_MONTH)}/" +
                "${cal.get(Calendar.MONTH) + 1}/" +
                "${cal.get(Calendar.YEAR)}"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}