package com.gajeks.electronicjournal.data.storage

interface LocalUserStorage {

    suspend fun getId(): Int

    suspend fun saveId(id: Int)

    suspend fun removeId()

    suspend fun getType(): String

    suspend fun saveType(type: String)

    suspend fun removeType()

}