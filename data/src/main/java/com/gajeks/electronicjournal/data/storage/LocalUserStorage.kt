package com.gajeks.electronicjournal.data.storage

interface LocalUserStorage {

    suspend fun getId(): Int

    suspend fun saveId(id: Int)

}