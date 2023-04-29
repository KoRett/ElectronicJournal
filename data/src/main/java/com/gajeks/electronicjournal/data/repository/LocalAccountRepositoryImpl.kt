package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository

class LocalAccountRepositoryImpl(
    private val localUserStorage: LocalUserStorage
) : LocalAccountRepository {

    override suspend fun getLocalId(): Int = localUserStorage.getId()

    override suspend fun saveLocalId(id: Int) = localUserStorage.saveId(id = id)

}