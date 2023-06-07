package com.gajeks.electronicjournal.presentation.tabs.student.schedule

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentStudentScheduleBinding
import com.gajeks.electronicjournal.domain.models.StudentLesson
import com.gajeks.electronicjournal.models.*
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class StudentScheduleFragment : BaseScheduleFragment() {

    private var _binding: FragmentStudentScheduleBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: Provider<StudentScheduleViewModel.Factory>
    override val vm: StudentScheduleViewModel by viewModels { vmFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentScheduleBinding.inflate(inflater, container, false)
        viewGroup = binding.root
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSet = mutableListOf<StudentLesson>()
        val adapter = getStudentScheduleAdapter(dataSet)

        binding.lessonList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = adapter
            edgeEffectFactory = BounceEdgeEffectFactory()
        }

        vm.lessons.observe(viewLifecycleOwner) { lessons ->
            renderedSimpleResult(binding.clSchedule, lessons) {
                dataSet.clear()
                dataSet.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
        vm.getSchedule()
    }

    private fun getStudentScheduleAdapter(dataset: List<StudentLesson>): StudentScheduleAdapter {
        val adapter = StudentScheduleAdapter(dataset)
        return adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}