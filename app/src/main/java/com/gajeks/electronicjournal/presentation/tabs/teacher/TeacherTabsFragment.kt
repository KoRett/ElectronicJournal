package com.gajeks.electronicjournal.presentation.tabs.teacher

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentTeacherTabsBinding
import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
import java.util.*
import javax.inject.Inject


class TeacherTabsFragment : Fragment() {

    private var _binding: FragmentTeacherTabsBinding? = null
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
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.setShowHideAnimationEnabled(false)
            it.show()
        }
        val cal = Calendar.getInstance()
        selectedDate.selectedWeekday = cal.get(Calendar.DAY_OF_WEEK)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherTabsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.tabs_teacher_schedule_graph,
            R.id.navigation_teacher_profile
        ).build()

        val navHost =
            childFragmentManager.findFragmentById(R.id.teacher_tabs_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(
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
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.setShowHideAnimationEnabled(false)
            it.hide()
        }
    }

}