package com.gajeks.electronicjournal.data.storage

interface UserStorageDatabase {
    suspend fun getPassword(email: String): String
    suspend fun getPassword(id: Int): String
    suspend fun getType(email: String): String
}