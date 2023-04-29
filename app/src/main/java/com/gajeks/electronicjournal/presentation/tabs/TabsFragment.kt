package com.gajeks.electronicjournal.presentation.tabs

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
import com.gajeks.electronicjournal.databinding.FragmentTabsBinding

class TabsFragment : Fragment() {

    private var _binding: FragmentTabsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabsBinding.inflate(layoutInflater, container, false)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_news,
            R.id.navigation_schedule,
            R.id.navigation_profile
        ).build()

        val navHost = childFragmentManager.findFragmentById(R.id.tabs_nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        setupActionBarWithNavController(requireActivity() as AppCompatActivity, navController, appBarConfiguration)

        binding.navBottomView.setupWithNavController(navController)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}