package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "lesson_id") val lessonId: Int,
    val weekday: Int,
    @ColumnInfo(name = "is_even_week") val isEvenWeek: Boolean,
    @ColumnInfo(name = "lesson_number") val lessonNumber: Int,
    @ColumnInfo(name = "is_seminar") val isSeminar: Boolean,
    @ColumnInfo(name = "lesson_name_id") val lessonNameId: Int,
    @ColumnInfo(name = "teacher_id") val teacherId: Int
) {
    companion object {
        const val TABLE_NAME = "lesson_table"
    }
}