package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity
import com.gajeks.electronicjournal.data.storage.room.entities.TeacherEntity

data class TeacherWithLessons(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "teacher_id",
        entityColumn = "teacher_id",
    )
    val teachers: List<LessonEntity>
)