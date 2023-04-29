package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class LessonNameEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
) {
    companion object {
        const val TABLE_NAME = "lesson_name_table"
    }
}