package com.gajeks.electronicjournal.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.databinding.FragmentChangingPasswordBinding

class ChangingPasswordFragment : Fragment() {

    private var _binding: FragmentChangingPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangingPasswordBinding.inflate(inflater, container, false)
        val navController = findNavController()
        binding.imBtBack.setOnClickListener{ navController.navigateUp() }
        binding.btChangePassword.setOnClickListener { navController.navigate(R.id.action_changingPasswordFragment_to_loginFragment) }
        binding.etNewPassword.doAfterTextChanged { binding.etNewPassword.isActivated = binding.etNewPassword.text.isNotEmpty() }
        binding.etConfirmationCode.doAfterTextChanged { binding.etConfirmationCode.isActivated = binding.etConfirmationCode.text.isNotEmpty() }
        binding.etConfirmationNewPassword.doAfterTextChanged { binding.etConfirmationNewPassword.isActivated = binding.etConfirmationNewPassword.text.isNotEmpty() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}