package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonsOfTeacherEntity
import com.gajeks.electronicjournal.data.storage.room.entities.TeacherEntity

data class TeacherWithLessons(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "teacher_id",
        associateBy = Junction(LessonsOfTeacherEntity::class)
    )
    val lessons: List<LessonEntity>
)

data class LessonWithTeachers(
    @Embedded val lesson: LessonNameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lesson_id",
        associateBy = Junction(LessonsOfTeacherEntity::class)
    )
    val teachers: List<TeacherEntity>
)