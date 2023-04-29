package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.*

data class StudentWithLessons(
    @Embedded val student: StudentEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "student_id",
        associateBy = Junction(AttendanceEntity::class)
    )
    val lessons: List<LessonEntity>
)

data class LessonsWithStudent(
    @Embedded val lesson: LessonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lesson_id",
        associateBy = Junction(AttendanceEntity::class)
    )
    val students: List<StudentEntity>
)