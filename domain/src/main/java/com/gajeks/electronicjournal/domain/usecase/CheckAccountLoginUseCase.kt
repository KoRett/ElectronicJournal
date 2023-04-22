package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.repository.UserRepository

class CheckAccountLoginUseCase(private val userRepository: UserRepository) {

    suspend fun execute(): Boolean{
        return userRepository.getId() != -1
    }

}