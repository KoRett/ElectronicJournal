package com.gajeks.electronicjournal.domain.repository

interface StudentRepository {

    suspend fun getId(email: String): Int?

    suspend fun getPassword(email: String): String?

    suspend fun setPassword(studentId: Int, newPassword: String)

    suspend fun getName(studentId: Int): PersonName?

    suspend fun getAttendance(studentId: Int): Int?

    suspend fun getGroup(studentId: Int): String?

}