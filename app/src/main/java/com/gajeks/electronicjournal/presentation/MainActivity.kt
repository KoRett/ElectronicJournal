package com.gajeks.electronicjournal.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.databinding.ActivityMainBinding
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase.Companion.STUDENT
import com.gajeks.electronicjournal.presentation.tabs.student.StudentTabsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    //TODO 1.1) Remove or reused destination listener
//    private val destinationListener =
//        NavController.OnDestinationChangedListener { _, destination, _ ->
//            if (isSignInDestination(destination))
//                supportActionBar?.hide()
//            else
//                supportActionBar?.show()
//        }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is StudentTabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        supportActionBar?.elevation = 0F

        val navController = getRootNavController()
        prepareRootNavController(isSignedIn(), navController)

        onNavControllerActivated(navController)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)

        setContentView(binding.root)
    }


    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        //TODO 1.2) Remove or reused destination listener
//        this.navController?.removeOnDestinationChangedListener(destinationListener)
//        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController?.navigateUp() ?: false) || super.onSupportNavigateUp()

    private fun prepareRootNavController(isSignedIn: String, navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(
            if (isSignedIn == STUDENT) {
                getStudentTabsDestination()
            } else {
                getSignInDestination()
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

//    private fun isSignInDestination(destination: NavDestination?): Boolean =
//        destination?.parent?.id == getMainGraphId()

    private fun getMainNavigationGraphId(): Int = R.navigation.main_graph
    private fun getStudentTabsDestination(): Int = R.id.tabs_student_graph
    private fun getSignInDestination(): Int = R.id.login_fragment
    private fun getMainGraphId(): Int = R.id.main_graph

    override fun onDestroy() {
        navController = null
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }
}