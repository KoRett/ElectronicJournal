package com.gajeks.electronicjournal.presentation.entrance.mail_request

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentMailRequestBinding
import com.gajeks.electronicjournal.databinding.PartLoadingBinding
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.models.changeBackground
import com.gajeks.electronicjournal.models.setBackground
import javax.inject.Inject
import javax.inject.Provider


class MailRequestFragment : BaseFragment() {

    private var _binding: FragmentMailRequestBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<MailRequestFragmentArgs>()

    @Inject
    lateinit var vmFactory: Provider<MailRequestViewModel.Factory>
    override val vm: MailRequestViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailRequestBinding.inflate(inflater, container, false)

        binding.etEmail.changeBackground()

        val navController = findNavController()

        binding.imBtBack.setOnClickListener { navController.popBackStack() }

        vm.resultLive.observe(viewLifecycleOwner) {
            val loadingBinding = PartLoadingBinding.bind(binding.root)
            this.renderResult(loadingBinding.fragmentLoading, it,
                onPending = {
                    loadingBinding.fragmentLoading.children.forEach { children ->
                        children.visibility = View.VISIBLE
                    }
                    setInteractionWithViews(isWork = false)
                },
                onError = {
                    loadingBinding.fragmentLoading.children.forEach { children ->
                        children.visibility = View.GONE
                    }
                    setInteractionWithViews(isWork = true)
                },
                onSuccess = {
                    navController.navigate(R.id.action_mail_request_fragment_to_changing_password_fragment)
                    vm.resetResult()
                    setInteractionWithViews(isWork = true)
                })
        }

        binding.btSend.setOnClickListener {
            vm.sendEmail(binding.etEmail.text.toString())
        }

        binding.etEmail.setText(getEmailArgument() ?: "")

        return binding.root
    }

    private fun setInteractionWithViews(isWork: Boolean) {
        val loadingBinding = PartLoadingBinding.bind(binding.root)
        binding.root.children.forEach { children ->
            if (children.id != loadingBinding.fragmentLoading.id) {
                children.isClickable = isWork
                children.isFocusableInTouchMode = isWork
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etEmail.setBackground(binding.etEmail.text.isNotEmpty())
    }

    private fun getEmailArgument(): String? = args.email

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}