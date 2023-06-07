package com.gajeks.electronicjournal.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.databinding.ActivityMainBinding
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase.Companion.STUDENT
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase.Companion.TEACHER
import com.gajeks.electronicjournal.presentation.tabs.student.StudentTabsFragment
import com.gajeks.electronicjournal.presentation.tabs.teacher.TeacherTabsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is StudentTabsFragment || f is NavHostFragment || f is TeacherTabsFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.let { actionBar ->
            actionBar.setShowHideAnimationEnabled(false)
            actionBar.elevation = 0F
            actionBar.hide()
        }

        val navController = getRootNavController()
        prepareRootNavController(isSignedIn(), navController)

        onNavControllerActivated(navController)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)

        setContentView(binding.root)
    }


    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController = navController
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController?.navigateUp() ?: false) || super.onSupportNavigateUp()

    private fun prepareRootNavController(isSignedIn: String, navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(
            when (isSignedIn) {
                STUDENT -> getStudentTabsDestination()
                TEACHER -> getTeacherTabsDestination()
                else -> getSignInDestination()
            }
        )
        navController.graph = graph
    }

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        return navHost.navController
    }

    private fun isSignedIn(): String {
        val bundle = intent.extras ?: throw IllegalStateException("No required arguments")
        val args = MainActivityArgs.fromBundle(bundle)
        return args.isSignedIn
    }

    private fun getMainNavigationGraphId(): Int = R.navigation.main_graph
    private fun getStudentTabsDestination(): Int = R.id.student_tabs_fragment
    private fun getTeacherTabsDestination(): Int = R.id.teacher_tabs_fragment
    private fun getSignInDestination(): Int = R.id.login_fragment

    override fun onNavigateUp(): Boolean {
        navController?.popBackStack()
        return true
    }

    override fun onDestroy() {
        navController = null
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }
}