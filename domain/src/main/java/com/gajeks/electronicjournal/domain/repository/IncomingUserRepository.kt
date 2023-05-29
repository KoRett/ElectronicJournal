package com.gajeks.electronicjournal.domain.repository

interface IncomingUserRepository {

    suspend fun sendEmailWithCode(code: Int, emailReceiver: String)

}