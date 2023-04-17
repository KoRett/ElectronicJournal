package com.gajeks.electronicjournal.domain.repository

interface UserRepository {
    fun getPassword(email: String): String
}