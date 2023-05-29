package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.TaskEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id") val taskId: Int,
    val name: String,
    val description: String
) {
    companion object {
        const val TABLE_NAME = "task_table"
    }
}