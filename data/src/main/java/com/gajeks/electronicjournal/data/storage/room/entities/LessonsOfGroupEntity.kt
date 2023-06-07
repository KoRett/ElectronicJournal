package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.gajeks.electronicjournal.data.storage.room.entities.LessonsOfGroupEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["lesson_id", "group_id"],
    indices = [
        Index("group_id")
    ]
)
data class LessonsOfGroupEntity(
    @ColumnInfo(name = "lesson_id") val lessonId: Int,
    @ColumnInfo(name = "group_id") val groupId: Int
) {
    companion object {
        const val TABLE_NAME = "lessons_of_group_table"
    }
}