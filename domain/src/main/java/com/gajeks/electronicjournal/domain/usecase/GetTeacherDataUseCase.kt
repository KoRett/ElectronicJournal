package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.TeacherData
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class GetTeacherDataUseCase(private val teacherRepository: TeacherRepository) {
    suspend fun exec(teacherId: Int): TeacherData {
        return TeacherData(
            personName = teacherRepository.getPersonName(teacherId)!!
        )
    }
}