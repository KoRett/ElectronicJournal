package com.gajeks.electronicjournal.presentation.tabs.teacher.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gajeks.electronicjournal.databinding.TeacherScheduleItemBinding
import com.gajeks.electronicjournal.domain.models.TeacherLesson

class TeacherScheduleAdapter(
    private val dataSet: List<TeacherLesson>
) : RecyclerView.Adapter<TeacherScheduleAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, lessonId: Int, view: View)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(val binding: TeacherScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = TeacherScheduleItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.binding) {
            textName.text = dataSet[position].lessonName
            textTime.text = getLessonTime(dataSet[position].lessonNumber)
            textType.text = getLessonType(dataSet[position].isSeminar)
            textGroups.text = "Группы: " + dataSet[position].groupsName[0]
            var i = 1
            while (i < dataSet[position].groupsName.size) {
                textGroups.text = "${textGroups.text}, ${dataSet[position].groupsName[i]}"
                i++
            }
            imBtMenu.setOnClickListener {
                listener?.onItemClick(position, dataSet[position].lessonId, it)
            }
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