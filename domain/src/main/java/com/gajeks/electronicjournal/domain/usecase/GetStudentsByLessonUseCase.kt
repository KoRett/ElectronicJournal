package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.StudentOfLesson
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class GetStudentsByLessonUseCase(private val lessonRepository: LessonRepository) {

    suspend fun exec(lessonId: Int, date: String): List<StudentOfLesson>? {
        return lessonRepository.getStudentsByLesson(lessonId, date)
    }

}