package com.gajeks.electronicjournal.presentation.tabs.teacher.schedule.attendance

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentTeacherAttendanceBinding
import com.gajeks.electronicjournal.databinding.PartResultBinding
import com.gajeks.electronicjournal.domain.models.StudentOfLesson
import com.gajeks.electronicjournal.domain.models.takeSuccess
import com.gajeks.electronicjournal.models.BaseFragment
import com.gajeks.electronicjournal.models.renderedSimpleResult
import javax.inject.Inject
import javax.inject.Provider

class TeacherAttendanceFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: Provider<TeacherAttendanceViewModel.Factory>
    override val vm: TeacherAttendanceViewModel by viewModels { vmFactory.get() }

    private var _binding: FragmentTeacherAttendanceBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<TeacherAttendanceFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherAttendanceBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar

        val bindingResult = PartResultBinding.bind(binding.studentsPart)
        bindingResult.progressBar.indeterminateTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataSet = mutableListOf<StudentOfLesson>()
        val adapter = getStudentListAdapter(dataSet)

        binding.studentList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = adapter
        }

        binding.btSave.setOnClickListener {
            vm.saveStudentAttendance(args.lessonId, args.date)
        }

        binding.textStudents.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                binding.root,
                AutoTransition().setDuration(200)
            )
            val animation: Animation
            if (binding.studentsPart.visibility == View.GONE) {
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate_minus_90_degrees)
                binding.studentsPart.visibility = View.VISIBLE
            } else {
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate_plus_90_degress)
                binding.studentsPart.visibility = View.GONE
            }
            animation.fillAfter = true
            binding.imStudents.startAnimation(animation)
        }

        vm.students.observe(viewLifecycleOwner) { result ->
            renderedSimpleResult(binding.studentsPart, result) {
                dataSet.clear()
                dataSet.addAll(result.takeSuccess()!!)
                adapter.notifyDataSetChanged()
            }
        }

        vm.saveAttendance.observe(viewLifecycleOwner) {
            renderLoading(binding.root, it,
                onPending = {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                },
                onError = {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                },
                onSuccess = {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                })
        }

        vm.getStudents(args.lessonId, args.date)
    }

    private fun getStudentListAdapter(dataSet: List<StudentOfLesson>): TeacherAttendanceAdapter {
        val adapter = TeacherAttendanceAdapter(dataSet)
        adapter.setOnItemClickListener(object :
            TeacherAttendanceAdapter.OnItemClickListener {
            override fun onItemClick(
                position: Int,
                studentId: Int,
                isAttended: Boolean
            ) {
                vm.setStudentAttendance(studentId, isAttended)
            }
        })
        return adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        _binding = null
    }
}