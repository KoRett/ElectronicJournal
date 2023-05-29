package com.gajeks.electronicjournal.data.storage.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.entities.GroupEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
)
class GroupEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "group_id") val groupId: Int,
    val name: String,
    val course: Int
) {
    companion object {
        const val TABLE_NAME = "group_table"
    }
}