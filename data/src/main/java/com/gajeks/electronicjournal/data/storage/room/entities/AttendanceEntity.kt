package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.AttendanceEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "attendance_id") val attendanceId: Int,
    @ColumnInfo(name = "student_id") val studentId: Int,
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "is_attended") var isAttended: Boolean,
    val date: String
) {
    companion object {
        const val TABLE_NAME = "attendance_table"
    }
}
