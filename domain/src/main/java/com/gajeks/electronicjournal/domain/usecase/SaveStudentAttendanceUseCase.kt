package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.StudentAttendance
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class SaveStudentAttendanceUseCase(private val lessonRepository: LessonRepository) {

    suspend fun exec(studentAttendance: StudentAttendance) {
        lessonRepository.saveStudentAttendanceByLesson(studentAttendance)
    }

}