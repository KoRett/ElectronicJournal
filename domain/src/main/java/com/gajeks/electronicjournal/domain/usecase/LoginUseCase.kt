package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class LoginUseCase(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository,
    private val localAccountRepository: LocalAccountRepository
) {
    suspend fun execute(userLoginParams: UserLoginParams): Boolean {
        val studentPassword = studentRepository.getPassword(email = userLoginParams.email)
        val teacherPassword = teacherRepository.getPassword(email = userLoginParams.email)

        var isSignIn = true

        if (studentPassword == userLoginParams.password)
            localAccountRepository.saveLocalId(
                id = studentRepository.getId(userLoginParams.email)!!
            )
        else if (teacherPassword == userLoginParams.password)
            localAccountRepository.saveLocalId(
                id = teacherRepository.getId(userLoginParams.email)!!
            )
        else
            isSignIn = false

        return isSignIn
    }
}