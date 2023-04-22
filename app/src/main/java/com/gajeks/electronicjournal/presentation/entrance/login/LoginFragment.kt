package com.gajeks.electronicjournal.presentation.entrance.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentLoginBinding
import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.models.BaseFragment
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

        etEmail = binding.etEmail
        etPassword = binding.etPassword

        etEmail?.doAfterTextChanged { etEmail?.isActivated = etEmail?.text!!.isNotEmpty() }
        etPassword?.doAfterTextChanged { etPassword?.isActivated = etPassword?.text!!.isNotEmpty() }

        vm.resultLive.observe(viewLifecycleOwner) {
            if (it)
                Toast.makeText(context, "TRUE", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "FALSE", Toast.LENGTH_SHORT).show()
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
            findNavController().navigate(R.id.action_loginFragment_to_mailRequestFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}