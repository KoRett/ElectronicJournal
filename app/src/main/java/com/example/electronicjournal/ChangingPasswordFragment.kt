package com.example.electronicjournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.electronicjournal.databinding.FragmentChangingPasswordBinding

class ChangingPasswordFragment : Fragment() {

    private var _binding: FragmentChangingPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangingPasswordBinding.inflate(inflater, container, false)
        binding.imBtBack.setOnClickListener{ activity?.onBackPressed() }
        binding.btChangePassword.setOnClickListener { findNavController().navigate(R.id.action_changingPasswordFragment_to_loginFragment) }
        binding.editTextNewPassword.doAfterTextChanged { binding.editTextNewPassword.isActivated = binding.editTextNewPassword.text.isNotEmpty() }
        binding.editTextConfirmationCode.doAfterTextChanged { binding.editTextConfirmationCode.isActivated = binding.editTextConfirmationCode.text.isNotEmpty() }
        binding.editTextConfirmationNewPassword.doAfterTextChanged { binding.editTextConfirmationNewPassword.isActivated = binding.editTextConfirmationNewPassword.text.isNotEmpty() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}