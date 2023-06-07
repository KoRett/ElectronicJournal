package com.gajeks.electronicjournal.data.models

import androidx.room.ColumnInfo

data class StudentAttendanceInDB (
    @ColumnInfo(name = "student_id") val studentId: Int,
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "is_attended") val isAttended: Boolean,
    val date: String
)
