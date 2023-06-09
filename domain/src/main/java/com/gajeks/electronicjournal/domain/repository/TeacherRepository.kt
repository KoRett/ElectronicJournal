package com.gajeks.electronicjournal.domain.repository

interface TeacherRepository {

    suspend fun getId(email: String): Int?
    suspend fun getPassword(email: String): String?
    suspend fun setPassword(teacherId: Int, newPassword: String)
    suspend fun getPersonName(teacherId: Int): PersonName?

}