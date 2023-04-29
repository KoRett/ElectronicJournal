package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gajeks.electronicjournal.data.storage.room.entities.ExerciseEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["lesson_id", "task_id"]
)
data class ExerciseEntity(
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "task_id") val taskId: Int
) {
    companion object {
        const val TABLE_NAME = "exercise_table"
    }
}