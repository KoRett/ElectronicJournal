package com.gajeks.electronicjournal.presentation.tabs.student.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.databinding.StudentScheduleItemBinding
import com.gajeks.electronicjournal.domain.models.StudentLesson

class StudentScheduleAdapter(private val dataSet: List<StudentLesson>) :
    RecyclerView.Adapter<StudentScheduleAdapter.ViewHolder>() {

    class ViewHolder(val binding: StudentScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = StudentScheduleItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.binding) {
            textName.text = dataSet[position].lessonName
            textTime.text = getLessonTime(dataSet[position].lessonNumber)
            textType.text = getLessonType(dataSet[position].isSeminar)
            val teacherName = dataSet[position].teacherName
            if (teacherName.patronymic != null)
                textTeacher.text =
                    "${teacherName.lastName} ${teacherName.firstName[0]}. ${teacherName.patronymic!![0]}."
            else
                textTeacher.text = "${teacherName.lastName} ${teacherName.firstName[0]}."
            imIsAttended.isActivated = dataSet[position].isAttended
        }
    }

    private fun getLessonTime(position: Int): String {
        return when (position) {
            1 -> "9:00\n10:30"
            2 -> "10:40\n12:10"
            3 -> "12:40\n14:10"
            4 -> "14:20\n15:50"
            5 -> "16:20\n17:50"
            6 -> "18:00\n19:30"
            else -> throw IllegalArgumentException()
        }
    }

    private fun getLessonType(type: Boolean): String {
        return when (type) {
            true -> "пр"
            false -> "лк"
        }
    }

    override fun getItemCount() = dataSet.size

}