package com.example.electronicjournal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.electronicjournal.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: LoginViewModel
    private lateinit var btLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var textForgotPassword: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        btLogin = binding.btLogin
        etEmail = binding.editTextEmail
        etPassword = binding.editTextPassword
        textForgotPassword = binding.textForgotPassword

        etEmail.doAfterTextChanged { etEmail.isActivated = etEmail.text.isNotEmpty() }
        etPassword.doAfterTextChanged { etPassword.isActivated = etPassword.text.isNotEmpty() }

        textForgotPassword.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_mailRequestFragment) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}