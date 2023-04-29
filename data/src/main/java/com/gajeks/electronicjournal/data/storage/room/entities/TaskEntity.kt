package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.TaskEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class TaskEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String
) {
    companion object {
        const val TABLE_NAME = "task_table"
    }
}