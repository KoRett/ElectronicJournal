package com.gajeks.electronicjournal.presentation.tabs.teacher.schedule

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.app.App
import com.gajeks.electronicjournal.databinding.FragmentTeacherScheduleBinding
import com.gajeks.electronicjournal.domain.models.TeacherLesson
import com.gajeks.electronicjournal.models.*
import javax.inject.Inject
import javax.inject.Provider

class TeacherScheduleFragment : BaseScheduleFragment() {

    private var _binding: FragmentTeacherScheduleBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: Provider<TeacherScheduleViewModel.Factory>
    override val vm: TeacherScheduleViewModel by viewModels { vmFactory.get() }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherScheduleBinding.inflate(inflater, container, false)
        viewGroup = binding.root
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSet = mutableListOf<TeacherLesson>()
        val adapter = getTeacherScheduleAdapter(dataSet)

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

    private fun getTeacherScheduleAdapter(dataSet: List<TeacherLesson>): TeacherScheduleAdapter {
        val adapter = TeacherScheduleAdapter(dataSet)
        adapter.setOnItemClickListener(object :
            TeacherScheduleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, lessonId: Int, view: View) {
                createPopupMenu(view, lessonId)
            }
        })
        return adapter
    }

    private fun createPopupMenu(view: View, lessonId: Int) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(
            getPopupMenu(),
            popupMenu.menu
        )
        popupMenu.setForceShowIcon(true)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                getPopupMenuAttendanceItem() -> {
                    onMarkAttendancePressed(lessonId)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun onMarkAttendancePressed(lessonId: Int) {
        val direction =
            TeacherScheduleFragmentDirections.actionTeacherScheduleFragmentToTeacherAttendanceFragment(
                lessonId,
                selectedDate.date!!
            )
        findNavController().navigate(direction)
    }

    private fun getPopupMenu() = R.menu.teacher_schedule_popup_menu
    private fun getPopupMenuAttendanceItem() = R.id.set_attendance

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}