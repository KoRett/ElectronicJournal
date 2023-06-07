package com.gajeks.electronicjournal.domain.repository

import com.gajeks.electronicjournal.domain.models.StudentAttendance
import com.gajeks.electronicjournal.domain.models.StudentLesson
import com.gajeks.electronicjournal.domain.models.StudentOfLesson
import com.gajeks.electronicjournal.domain.models.TeacherLesson

interface LessonRepository {

    suspend fun getStudentLessons(studentId: Int, date: String): List<StudentLesson>?
    suspend fun getTeacherLessons(teacherId: Int): List<TeacherLesson>?
    suspend fun getStudentsByLesson(lessonId: Int, date: String): List<StudentOfLesson>?
    suspend fun saveStudentAttendanceByLesson(studentAttendance: StudentAttendance)

}