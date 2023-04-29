package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gajeks.electronicjournal.data.storage.room.entities.TeacherEntity

@Dao
interface TeacherDao {

    @Query("SELECT password FROM ${TeacherEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getPassword(email: String): String?

    @Query("SELECT password FROM ${TeacherEntity.TABLE_NAME} WHERE id LIKE :id")
    suspend fun getPassword(id: Int): String?

    @Query("SELECT id FROM ${TeacherEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getId(email: String): Int?

    @Insert(entity = TeacherEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(teacherEntity: TeacherEntity)

}