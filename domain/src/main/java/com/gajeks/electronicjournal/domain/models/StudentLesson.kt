package com.gajeks.electronicjournal.domain.models

import com.gajeks.electronicjournal.domain.repository.PersonName

data class StudentLesson(
    val weekday: Int,
    val isEvenWeek: Boolean,
    val lessonNumber: Int,
    val isSeminar: Boolean,
    val lessonName: String,
    val teacherName: PersonName,
    val isAttended: Boolean
)
