package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.domain.models.UserParams
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class LoginUseCase(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository,
    private val localAccountRepository: LocalAccountRepository
) {
    suspend fun execute(userLoginParams: UserLoginParams): String? {
        val studentPassword = studentRepository.getPassword(email = userLoginParams.email)
        val teacherPassword = teacherRepository.getPassword(email = userLoginParams.email)

        var isSignIn: String? = null

        if (studentPassword == userLoginParams.password) {
            isSignIn = STUDENT
            UserParams.id = studentRepository.getId(userLoginParams.email)!!
            localAccountRepository.saveLocalId(
                id = UserParams.id!!
            )
            localAccountRepository.saveLocalType(type = STUDENT)
        } else if (teacherPassword == userLoginParams.password) {
            isSignIn = TEACHER
            UserParams.id = teacherRepository.getId(userLoginParams.email)!!
            localAccountRepository.saveLocalId(
                id = UserParams.id!!
            )
            localAccountRepository.saveLocalType(type = TEACHER)
        }

        return isSignIn
    }

    companion object {
        const val STUDENT = "student"
        const val TEACHER = "teacher"
    }
}