package com.gajeks.electronicjournal.domain.usecase

import com.gajeks.electronicjournal.domain.models.TeacherLesson
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class GetTeacherLessonsUseCase(private val lessonRepository: LessonRepository) {

    suspend fun execute(
        teacherId: Int,
        isEvenWeek: Boolean,
        weekday: Int
    ): List<TeacherLesson>? {
        val allLessons = lessonRepository.getTeacherLessons(teacherId)
        var lessons: MutableList<TeacherLesson>? = null

        allLessons?.forEach { lesson ->
            if ((lesson.isEvenWeek == isEvenWeek) and (lesson.weekday == weekday)) {
                if (lessons == null)
                    lessons = mutableListOf(lesson)
                else
                    lessons!!.add(lesson)
            }
        }

        return lessons?.sortedWith(comparator = compareBy(TeacherLesson::lessonNumber))
    }

}