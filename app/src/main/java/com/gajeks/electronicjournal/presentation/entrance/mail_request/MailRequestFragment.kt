package com.gajeks.electronicjournal.presentation.entrance.mail_request

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentMailRequestBinding
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.etEmail.changeBackground()
        binding.etEmail.setText(getEmailArgument() ?: "")

        binding.btSend.setOnClickListener {
            vm.sendEmail(binding.etEmail.text.toString())
        }

        binding.imBtBack.setOnClickListener { navController.popBackStack() }

        vm.resultLive.observe(viewLifecycleOwner) {
            this.renderLoading(binding.root, it,
                onPending = {},
                onError = {},
                onSuccess = {
                    navController.navigate(R.id.action_mail_request_fragment_to_changing_password_fragment)
                    vm.resetResult()
                })
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