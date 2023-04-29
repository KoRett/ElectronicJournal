package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.*

data class StudentWithTasks(
    @Embedded val student: StudentEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "student_id",
        associateBy = Junction(EstimationEntity::class)
    )
    val tasks: List<TaskEntity>
)

data class TaskWithStudents(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        associateBy = Junction(EstimationEntity::class)
    )
    val students: List<StudentEntity>
)