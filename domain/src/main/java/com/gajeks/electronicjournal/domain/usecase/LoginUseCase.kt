package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userLoginParams: UserLoginParams): Boolean {
        return userLoginParams.password == userRepository.getPassword(userLoginParams.email)
    }
}