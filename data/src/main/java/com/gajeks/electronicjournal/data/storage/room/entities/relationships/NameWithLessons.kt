package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity

data class NameWithLessons(
    @Embedded val lessonName: LessonNameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lesson_name_id"
    )
    val lessons: List<LessonEntity>
)