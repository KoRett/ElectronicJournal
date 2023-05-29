package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.CodeException
import com.gajeks.electronicjournal.domain.models.PasswordException
import com.gajeks.electronicjournal.domain.models.SecurityCode
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class ChangePasswordUseCase(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
) {
    suspend fun exec(code: Int, newPassword: String, confirmNewPassword: String) {
        if (SecurityCode.code == code) {
            if (newPassword == confirmNewPassword) {
                var id: Int? = studentRepository.getId(SecurityCode.email!!)
                if (id != null) {
                    studentRepository.setPassword(studentId = id, newPassword = newPassword)
                    return
                }

                id = teacherRepository.getId(SecurityCode.email!!)
                if (id != null) {
                    teacherRepository.setPassword(teacherId = id, newPassword = newPassword)
                    return
                }
                throw RuntimeException()
            } else
                throw PasswordException()
        } else
            throw CodeException()
    }
}