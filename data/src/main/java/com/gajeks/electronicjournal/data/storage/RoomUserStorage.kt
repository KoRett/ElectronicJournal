package com.gajeks.electronicjournal.data.storage

import com.gajeks.electronicjournal.data.storage.room.UserDao

class RoomUserStorage(private val userDao: UserDao) : UserStorageDatabase {

    override suspend fun getPassword(email: String): String {
        return userDao.getPassword(email = email)
    }

    override suspend fun getPassword(id: Int): String {
        return userDao.getPassword(id = id)
    }

    override suspend fun getType(email: String): String {
        return userDao.getType(email = email)
    }
}