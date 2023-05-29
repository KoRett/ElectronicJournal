package com.gajeks.electronicjournal.domain.repository

import com.gajeks.electronicjournal.domain.models.StudentLesson

interface LessonRepository {

    suspend fun getStudentLessons(studentId: Int, date: String): List<StudentLesson>?

    suspend fun getTeacherLessons(studentId: Int)

}