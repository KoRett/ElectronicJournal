package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.gajeks.electronicjournal.data.storage.room.entities.GroupEntity
import com.gajeks.electronicjournal.data.storage.room.entities.relationships.GroupWithLessons

@Dao
interface GroupDao {

    @Transaction
    @Query("SELECT * FROM ${GroupEntity.TABLE_NAME} WHERE group_id LIKE :groupId")
    suspend fun getLessons(groupId: Int): GroupWithLessons?

    @Query("SELECT name FROM ${GroupEntity.TABLE_NAME} WHERE group_id LIKE :groupId")
    suspend fun getName(groupId: Int): String?

}