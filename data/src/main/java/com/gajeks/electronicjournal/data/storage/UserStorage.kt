package com.gajeks.electronicjournal.data.storage

interface UserStorage {
    fun getPassword(email: String): String
}