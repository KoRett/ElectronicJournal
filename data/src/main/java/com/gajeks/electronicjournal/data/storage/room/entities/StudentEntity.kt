package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.*
import com.gajeks.electronicjournal.data.storage.room.entities.StudentEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index("email")
    ]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val patronymic: String?,
    @ColumnInfo(name = "group_id") val groupId: Int?
) {

    companion object {
        const val TABLE_NAME = "student_table"
    }

}