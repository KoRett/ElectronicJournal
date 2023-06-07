package com.gajeks.electronicjournal.presentation.tabs.teacher.schedule.attendance

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.databinding.StudentListItemBinding
import com.gajeks.electronicjournal.domain.models.StudentOfLesson

class TeacherAttendanceAdapter(
    private val dataSet: List<StudentOfLesson>
) : RecyclerView.Adapter<TeacherAttendanceAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, studentId: Int, isAttended: Boolean)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(
        val binding: StudentListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = StudentListItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.textName.text = with(dataSet[position].personName) {
            if (patronymic != null) {
                "$lastName $firstName $patronymic"
            } else {
                "$lastName $firstName"
            }
        }
        with(viewHolder.binding) {
            imIsAttended.isActivated = dataSet[position].isAttended
            textName.setOnClickListener {
                imIsAttended.isActivated = !imIsAttended.isActivated
                listener?.onItemClick(
                    position,
                    dataSet[position].studentId,
                    imIsAttended.isActivated
                )
            }
        }
    }

    override fun getItemCount() = dataSet.size

}