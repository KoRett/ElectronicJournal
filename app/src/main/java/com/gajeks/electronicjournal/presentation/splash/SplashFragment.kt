package com.gajeks.electronicjournal.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentSplashBinding
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Provider

private val KEY_IS_SIGNED_IN = "is_signed_in"

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: Provider<SplashViewModel.Factory>
    override val vm: SplashViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        vm.resultLive.observe(viewLifecycleOwner){
            launchMainScreen(it)
        }

        return binding.root
    }

    private fun launchMainScreen(isSignedIn: Boolean){
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = Bundle()
        args.putBoolean(KEY_IS_SIGNED_IN, isSignedIn)
        intent.putExtras(args)

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}