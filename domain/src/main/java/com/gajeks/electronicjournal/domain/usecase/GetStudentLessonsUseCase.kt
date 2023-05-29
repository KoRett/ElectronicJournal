package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.StudentLesson
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class GetStudentLessonsUseCase(private val lessonRepository: LessonRepository) {

    suspend fun execute(
        studentId: Int,
        isEvenWeek: Boolean,
        weekday: Int,
        date: String
    ): List<StudentLesson>? {
        val allLessons = lessonRepository.getStudentLessons(studentId, date)
        var lessons: MutableList<StudentLesson>? = null

        allLessons?.forEach { lesson ->
            if ((lesson.isEvenWeek == isEvenWeek) and (lesson.weekday == weekday)) {
                if (lessons == null)
                    lessons = mutableListOf(lesson)
                else
                    lessons!!.add(lesson)
            }
        }

        return lessons?.sortedWith(comparator = compareBy(StudentLesson::lessonNumber))
    }

}