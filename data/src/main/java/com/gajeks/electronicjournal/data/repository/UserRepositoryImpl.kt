package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.UserStorage
import com.gajeks.electronicjournal.domain.repository.UserRepository

class UserRepositoryImpl (private val userStorage: UserStorage) : UserRepository {
    override fun getPassword(email: String): String {
        return userStorage.getPassword(email)
    }
}