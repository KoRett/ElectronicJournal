package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.StudentData
import com.gajeks.electronicjournal.domain.repository.StudentRepository

class GetStudentDataUseCase(private val studentRepository: StudentRepository) {

    suspend fun exec(studentId: Int): StudentData {
        return StudentData(
            personName = studentRepository.getName(studentId)!!,
            group = studentRepository.getGroup(studentId)!!,
            attendance = studentRepository.getAttendance(studentId)!!
        )
    }

}