package com.gajeks.electronicjournal.presentation.entrance.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentLoginBinding
import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase.Companion.STUDENT
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.models.changeBackground
import com.gajeks.electronicjournal.models.isValidEmail
import com.gajeks.electronicjournal.models.setBackground
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var etEmail: EditText? = null
    private var etPassword: EditText? = null

    @Inject
    lateinit var vmFactory: Provider<LoginViewModel.Factory>
    override val vm: LoginViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        etEmail = binding.etEmail
        etPassword = binding.etPassword

        etEmail?.changeBackground()
        etPassword?.changeBackground()

        vm.resultLive.observe(viewLifecycleOwner) {
            if (it == STUDENT) {
                findNavController().navigate(R.id.action_login_fragment_to_tabs_student_fragment)
            }
            else
                binding.textError.visibility = View.VISIBLE
        }

        binding.btLogin.setOnClickListener {
            vm.login(
                UserLoginParams(
                    email = etEmail?.text.toString(),
                    password = etPassword?.text.toString()
                )
            )
        }

        binding.textForgotPassword.setOnClickListener {
            onForgotPasswordTextPressed()
        }

        return binding.root
    }

    private fun onForgotPasswordTextPressed() {
        val email = binding.etEmail.text.toString()
        val emailArg = if (email.isValidEmail()) {
            email
        } else {
            null
        }

        val direction = LoginFragmentDirections.actionLoginFragmentToMailRequestFragment(emailArg)

        findNavController().navigate(direction)
    }

    override fun onResume() {
        super.onResume()
        binding.etEmail.setBackground(binding.etEmail.text.isNotEmpty())
        binding.etPassword.setBackground(binding.etPassword.text.isNotEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}