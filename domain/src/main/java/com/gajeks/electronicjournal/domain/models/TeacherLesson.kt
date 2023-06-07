package com.gajeks.electronicjournal.domain.models

data class TeacherLesson(
    val weekday: Int,
    val isEvenWeek: Boolean,
    val lessonNumber: Int,
    val isSeminar: Boolean,
    val lessonName: String,
    val groupsName: List<String>,
    val lessonId: Int
)
