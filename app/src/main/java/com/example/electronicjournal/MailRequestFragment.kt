package com.example.electronicjournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.electronicjournal.databinding.FragmentMailRequestBinding

class MailRequestFragment : Fragment() {

    private var _binding: FragmentMailRequestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailRequestBinding.inflate(inflater, container, false)
        binding.imBtBack.setOnClickListener { activity?.onBackPressed() }
        binding.btSend.setOnClickListener { findNavController().navigate(R.id.action_mailRequestFragment_to_changingPasswordFragment) }
        binding.editTextEmail.doAfterTextChanged { binding.editTextEmail.isActivated = binding.editTextEmail.text.isNotEmpty() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}