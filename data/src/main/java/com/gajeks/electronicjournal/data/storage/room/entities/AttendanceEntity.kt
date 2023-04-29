package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gajeks.electronicjournal.data.storage.room.entities.AttendanceEntity.Companion.TABLE_NAME


@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["student_id", "lesson_id"]
)
data class AttendanceEntity(
    @ColumnInfo(name = "student_id") val studentId: Int,
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "is_attended") val isAttended: Boolean
) {
    companion object {
        const val TABLE_NAME = "attendance_table"
    }
}
