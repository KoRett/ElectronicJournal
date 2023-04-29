package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.SuccessResult
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository

class CheckAccountLoginUseCase(private val localAccountRepository: LocalAccountRepository) {

    suspend fun execute(): SuccessResult<Boolean> {
        return SuccessResult(localAccountRepository.getLocalId() != -1)
    }

}