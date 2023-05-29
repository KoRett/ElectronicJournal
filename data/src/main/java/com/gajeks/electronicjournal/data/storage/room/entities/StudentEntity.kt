package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.*
import com.gajeks.electronicjournal.data.models.PersonNameInDB
import com.gajeks.electronicjournal.data.storage.room.entities.StudentEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index("email")
    ]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "student_id") val studentId: Int,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    @Embedded val personNameInDB: PersonNameInDB,
    @ColumnInfo(name = "group_id") val groupId: Int?
) {

    companion object {
        const val TABLE_NAME = "student_table"
    }

}