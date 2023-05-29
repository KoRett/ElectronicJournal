package com.gajeks.electronicjournal.data.models

import androidx.room.ColumnInfo
import com.gajeks.electronicjournal.domain.repository.PersonName

data class PersonNameInDB(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val patronymic: String?
) {
    fun map(): PersonName {
        return PersonName(
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic
        )
    }
}