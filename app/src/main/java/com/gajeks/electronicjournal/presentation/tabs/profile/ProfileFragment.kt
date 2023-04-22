package com.gajeks.electronicjournal.presentation.tabs.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentProfileBinding
import com.gajeks.electronicjournal.models.BaseFragment
import javax.inject.Inject
import javax.inject.Provider

class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var vmFactory: Provider<ProfileViewModel.Factory>
    override val vm: ProfileViewModel by viewModels{ vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}