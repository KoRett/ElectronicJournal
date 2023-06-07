package com.gajeks.electronicjournal.presentation.tabs.student

import android.annotation.SuppressLint
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
import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
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

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setShowHideAnimationEnabled(false)
            actionBar.show()
        }
        val cal = Calendar.getInstance()
        selectedDate.selectedWeekday = cal.get(Calendar.DAY_OF_WEEK)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentTabsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.tabs_student_schedule_graph,
            R.id.navigation_student_profile
        ).build()

        val navHost =
            childFragmentManager.findFragmentById(R.id.teacher_tabs_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        setupActionBarWithNavController(
            requireActivity() as AppCompatActivity,
            navController,
            appBarConfiguration
        )

        binding.navBottomView.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("RestrictedApi")
    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setShowHideAnimationEnabled(false)
            actionBar.hide()
        }
    }

}