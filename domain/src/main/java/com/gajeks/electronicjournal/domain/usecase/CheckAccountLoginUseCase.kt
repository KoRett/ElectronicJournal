package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.UserParams
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository

class CheckAccountLoginUseCase(private val localAccountRepository: LocalAccountRepository) {

    suspend fun execute(): String {
        val type = localAccountRepository.getLocalType()
        if (type != DEFAULT_TYPE)
            UserParams.id = localAccountRepository.getLocalId()
        return type
    }

    companion object {
        const val DEFAULT_TYPE = "none"
        const val DEFAULT_ID = -1
    }

}