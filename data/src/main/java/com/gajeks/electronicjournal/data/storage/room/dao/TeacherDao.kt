package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gajeks.electronicjournal.data.models.PersonNameInDB
import com.gajeks.electronicjournal.data.storage.room.entities.TeacherEntity

@Dao
interface TeacherDao {

    @Query("SELECT password FROM ${TeacherEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getPassword(email: String): String?

    @Query("SELECT first_name, last_name, patronymic FROM ${TeacherEntity.TABLE_NAME} WHERE teacher_id like :teacherId")
    suspend fun getName(teacherId: Int): PersonNameInDB

    @Query("SELECT password FROM ${TeacherEntity.TABLE_NAME} WHERE teacher_id LIKE :teacherId")
    suspend fun getPassword(teacherId: Int): String?

    @Query("UPDATE ${TeacherEntity.TABLE_NAME} SET password = :newPassword WHERE teacher_id LIKE :teacherId")
    suspend fun setPassword(teacherId: Int, newPassword: String)

    @Query("SELECT teacher_id FROM ${TeacherEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getId(email: String): Int?

    @Insert(entity = TeacherEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(teacherEntity: TeacherEntity)

}