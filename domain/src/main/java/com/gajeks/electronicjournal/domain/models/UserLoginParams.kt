package com.gajeks.electronicjournal.domain.models

data class UserLoginParams(
    val email: String,
    val password: String
)