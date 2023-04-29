package com.gajeks.electronicjournal.domain.repository

interface LocalAccountRepository {

    suspend fun getLocalId(): Int

    suspend fun saveLocalId(id: Int)

}