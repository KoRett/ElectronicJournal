package com.gajeks.electronicjournal.domain.models

import com.gajeks.electronicjournal.domain.repository.PersonName

data class StudentOfLesson(
    val studentId: Int,
    val personName: PersonName,
    val isAttended: Boolean
)
