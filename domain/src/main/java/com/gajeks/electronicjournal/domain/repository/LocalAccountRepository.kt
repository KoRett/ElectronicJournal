package com.gajeks.electronicjournal.domain.repository

interface LocalAccountRepository {

    suspend fun getLocalId(): Int

    suspend fun saveLocalId(id: Int)

    suspend fun removeLocalId()

    suspend fun getLocalType(): String

    suspend fun saveLocalType(type: String)

    suspend fun removeLocalType()

}