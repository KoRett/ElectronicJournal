package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository

class LogoutUseCase(private val localAccountRepository: LocalAccountRepository) {
    suspend fun execute() {
        localAccountRepository.removeLocalId()
        localAccountRepository.removeLocalType()
    }
}