package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.GroupEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity
import com.gajeks.electronicjournal.data.storage.room.entities.LessonsOfGroupEntity

data class GroupWithLessons(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "group_id",
        associateBy = Junction(LessonsOfGroupEntity::class)
    )
    val lessons: List<LessonNameEntity>
)

data class LessonWithGroups(
    @Embedded val lesson: LessonNameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "lesson_id",
        associateBy = Junction(LessonsOfGroupEntity::class)
    )
    val groups: List<GroupEntity>
)