package com.gajeks.electronicjournal.domain.models

import com.gajeks.electronicjournal.domain.repository.PersonName

data class StudentData(
    val personName: PersonName,
    val group: String,
    val attendance: Int
)