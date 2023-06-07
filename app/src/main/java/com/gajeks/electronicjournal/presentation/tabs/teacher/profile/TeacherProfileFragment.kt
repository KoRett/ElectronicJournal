package com.gajeks.electronicjournal.presentation.tabs.teacher.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentTeacherProfileBinding
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.models.findTopNavController
import javax.inject.Inject
import javax.inject.Provider

class TeacherProfileFragment : BaseFragment() {

    private var _binding: FragmentTeacherProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: Provider<TeacherProfileViewModel.Factory>
    override val vm: TeacherProfileViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textLogout.setOnClickListener {
            vm.logout()
            findTopNavController().navigate(R.id.action_tabs_teacher_fragment_to_login_fragment)
        }

        binding.textOptions.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                binding.cardOptions,
                AutoTransition().setDuration(200)
            )
            val animation: Animation
            if (binding.optionsList.visibility == View.GONE) {
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate_minus_90_degrees)
                binding.optionsList.visibility = View.VISIBLE
            } else {
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate_plus_90_degress)
                binding.optionsList.visibility = View.GONE
            }
            animation.fillAfter = true
            binding.imOptions.startAnimation(animation)
        }

        vm.teacher.observe(viewLifecycleOwner) { result ->
            renderResult(binding.cardProfile, result,
                onPending = {
                },
                onError = {
                },
                onSuccess = { data ->
                    binding.cardProfile.children.forEach { it.visibility = View.VISIBLE }
                    binding.textName.text =
                        "${data.personName.lastName} ${data.personName.firstName} ${data.personName.patronymic}"
                    binding.textRole.text = "Преподаватель"
                })
        }

        vm.getTeacherData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}