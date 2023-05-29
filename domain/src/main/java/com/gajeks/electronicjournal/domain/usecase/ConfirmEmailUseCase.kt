package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.SecurityCode
import com.gajeks.electronicjournal.domain.repository.IncomingUserRepository
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class ConfirmEmailUseCase(
    private val incomingUserRepository: IncomingUserRepository,
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository
) {
    suspend fun exec(emailReceiver: String): Boolean {
        val isAccountExist = (teacherRepository.getId(email = emailReceiver) != null) or
                (studentRepository.getId(email = emailReceiver) != null)

        return if (isAccountExist) {
            val code = (100000..999999).random()
            try {
                incomingUserRepository.sendEmailWithCode(code = code, emailReceiver = emailReceiver)
                SecurityCode.code = code
                SecurityCode.email = emailReceiver
                true
            } catch (_: RuntimeException) {
                false
            }
        } else
            false
    }
}