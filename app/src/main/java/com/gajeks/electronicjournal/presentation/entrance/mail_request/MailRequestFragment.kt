package com.gajeks.electronicjournal.presentation.entrance.mail_request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.databinding.FragmentMailRequestBinding

class MailRequestFragment : Fragment() {

    private var _binding: FragmentMailRequestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailRequestBinding.inflate(inflater, container, false)
        val navController = findNavController()
        binding.imBtBack.setOnClickListener { navController.navigateUp() }
        binding.btSend.setOnClickListener { navController.navigate(R.id.action_mailRequestFragment_to_changingPasswordFragment) }
        binding.etEmail.doAfterTextChanged { binding.etEmail.isActivated = binding.etEmail.text.isNotEmpty() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}