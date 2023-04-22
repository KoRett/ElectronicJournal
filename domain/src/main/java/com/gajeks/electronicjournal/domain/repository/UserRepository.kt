package com.gajeks.electronicjournal.domain.repository

interface UserRepository {
    suspend fun getId(): Int
    suspend fun saveId(id: Int)
    suspend fun getPassword(email: String): String
}