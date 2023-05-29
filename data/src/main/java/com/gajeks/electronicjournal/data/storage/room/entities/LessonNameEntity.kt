package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class LessonNameEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("lesson_name_id") val lessonNameId: Int,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "lesson_name_table"
    }
}