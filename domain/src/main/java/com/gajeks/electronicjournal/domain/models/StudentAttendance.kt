package com.gajeks.electronicjournal.domain.models

data class StudentAttendance(
    val studentId: Int,
    val lessonId: Int,
    val date: String,
    val isAttended: Boolean
)
