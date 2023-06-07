package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.*
import com.gajeks.electronicjournal.data.models.PersonNameInDB
import com.gajeks.electronicjournal.data.storage.room.entities.StudentEntity

@Dao
interface StudentDao {

    @Query("SELECT password FROM ${StudentEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getPassword(email: String): String?

    @Query("SELECT * FROM ${StudentEntity.TABLE_NAME} WHERE group_id LIKE :groupId")
    suspend fun getStudents(groupId: Int): List<StudentEntity>?

    @Query("SELECT password FROM ${StudentEntity.TABLE_NAME} WHERE student_id LIKE :studentId")
    suspend fun getPassword(studentId: Int): String?

    @Query("UPDATE ${StudentEntity.TABLE_NAME} SET password = :newPassword WHERE student_id LIKE :studentId")
    suspend fun setPassword(studentId: Int, newPassword: String)

    @Query("SELECT student_id FROM ${StudentEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getId(email: String): Int?

    @Query("SELECT group_id FROM ${StudentEntity.TABLE_NAME} WHERE student_id LIKE :studentId")
    suspend fun getGroupId(studentId: Int): Int?

    @Query("SELECT first_name, last_name, patronymic FROM ${StudentEntity.TABLE_NAME} WHERE student_id LIKE :studentId")
    suspend fun getName(studentId: Int): PersonNameInDB?

}