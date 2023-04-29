package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonsOfTeacherEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["lesson_id", "teacher_id"]
)
data class LessonsOfTeacherEntity(
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "teacher_id") val teacherId: Int
) {
    companion object {
        const val TABLE_NAME = "lessons_of_teacher_table"
    }
}