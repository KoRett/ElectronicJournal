package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
)
data class LessonEntity(
    @PrimaryKey val id: Int,
    val weekday: Int,
    @ColumnInfo(name = "lesson_number") val lessonNumber: Int,
    @ColumnInfo(name = "lesson_name_id") val lessonNameId: Int
) {
    companion object {
        const val TABLE_NAME = "lesson_table"
    }
}