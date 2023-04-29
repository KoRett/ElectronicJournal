package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.ExerciseEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity
import com.gajeks.electronicjournal.data.storage.room.entities.TaskEntity

data class TaskWithLessons(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        associateBy = Junction(ExerciseEntity::class)
    )
    val lessons: List<LessonEntity>
)

data class LessonWithTasks(
    @Embedded val lesson: LessonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lesson_id",
        associateBy = Junction(ExerciseEntity::class)
    )
    val tasks: List<TaskEntity>
)