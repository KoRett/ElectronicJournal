package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gajeks.electronicjournal.data.storage.room.entities.EstimationEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["task_id", "student_id"]
)
data class EstimationEntity(
    @ColumnInfo(name = "task_id") val taskId: Int,
    @ColumnInfo(name = "student_id") val studentId: Int,
    @ColumnInfo(name = "number_of_points") val numberOfPoints: Int
) {
    companion object {
        const val TABLE_NAME = "estimation_table"
    }
}
