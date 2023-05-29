package com.gajeks.electronicjournal.data.storage.room.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.gajeks.electronicjournal.data.storage.room.entities.GroupEntity
import com.gajeks.electronicjournal.data.storage.room.entities.StudentEntity

data class GroupWithStudents(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_id"
    )
    val students: List<StudentEntity>
)