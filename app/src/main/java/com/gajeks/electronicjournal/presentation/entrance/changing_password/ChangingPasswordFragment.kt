package com.gajeks.electronicjournal.presentation.entrance.changing_password

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentChangingPasswordBinding
import com.gajeks.electronicjournal.databinding.PartLoadingBinding
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.models.changeBackground
import javax.inject.Inject
import javax.inject.Provider

class ChangingPasswordFragment : BaseFragment() {

    private var _binding: FragmentChangingPasswordBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: Provider<ChangingPasswordViewModel.Factory>
    override val vm: ChangingPasswordViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangingPasswordBinding.inflate(inflater, container, false)
        val navController = findNavController()
        binding.imBtBack.setOnClickListener { navController.popBackStack() }

        vm.resultLive.observe(viewLifecycleOwner) { result ->
            val loadingBinding = PartLoadingBinding.bind(binding.root)
            this.renderResult(loadingBinding.fragmentLoading, result,
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
                    navController.navigate(R.id.action_changing_password_fragment_to_login_fragment)
                })
        }

        binding.btChangePassword.setOnClickListener {
            if (binding.etConfirmationCode.text.isNotEmpty() and
                binding.etNewPassword.text.isNotEmpty() and
                binding.etConfirmationNewPassword.text.isNotEmpty()
            )
                vm.changePassword(
                    binding.etConfirmationCode.text.toString().toInt(),
                    binding.etNewPassword.text.toString(),
                    binding.etConfirmationNewPassword.text.toString()
                )
        }

        binding.etNewPassword.changeBackground()
        binding.etConfirmationCode.changeBackground()
        binding.etConfirmationNewPassword.changeBackground()

        return binding.root
    }

    private fun setInteractionWithViews(isWork: Boolean) {
        binding.root.children.forEach { children ->
            val loadingBinding = PartLoadingBinding.bind(binding.root)
            if (children.id != loadingBinding.fragmentLoading.id) {
                children.isClickable = isWork
                children.isFocusableInTouchMode = isWork
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}