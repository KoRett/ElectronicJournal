package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.data.storage.UserStorageDatabase
import com.gajeks.electronicjournal.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorageDatabase: UserStorageDatabase,
    private val localUserStorage: LocalUserStorage
) : UserRepository {
    override suspend fun getId(): Int {
        return localUserStorage.getId()
    }

    override suspend fun saveId(id: Int) {
        localUserStorage.saveId(id)
    }

    override suspend fun getPassword(email: String): String {
        return userStorageDatabase.getPassword(email)
    }
}