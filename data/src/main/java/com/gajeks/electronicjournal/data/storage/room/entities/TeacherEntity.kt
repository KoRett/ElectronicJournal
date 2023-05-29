package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.*
import com.gajeks.electronicjournal.data.models.PersonNameInDB
import com.gajeks.electronicjournal.data.storage.room.entities.TeacherEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index("email")
    ]
)
data class TeacherEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "teacher_id") val teacherId: Int,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    @Embedded val personNameInDB: PersonNameInDB
) {

    companion object {
        const val TABLE_NAME = "teacher_table"
    }

}