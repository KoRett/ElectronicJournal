package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.room.dao.*
import com.gajeks.electronicjournal.domain.models.StudentLesson
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class LessonRepositoryImpl(
    private val studentDao: StudentDao,
    private val groupDao: GroupDao,
    private val lessonNameDao: LessonNameDao,
    private val teacherDao: TeacherDao,
    private val attendanceDao: AttendanceDao
) : LessonRepository {

    override suspend fun getStudentLessons(studentId: Int, date: String): List<StudentLesson>? {
        //TODO Remove Log.d(...)
        return groupDao.getLessons(studentDao.getGroupId(studentId = studentId)!!)?.get(0)
            ?.lessons?.map { lesson ->
                StudentLesson(
                    weekday = lesson.weekday,
                    isEvenWeek = lesson.isEvenWeek,
                    lessonNumber = lesson.lessonNumber,
                    isSeminar = lesson.isSeminar,
                    lessonName = lessonNameDao.getName(lessonNameId = lesson.lessonNameId),
                    teacherName = teacherDao.getName(lesson.teacherId).map(),
                    isAttended = attendanceDao.getAttendance(
                        studentId = studentId,
                        lessonId = lesson.lessonId,
                        date = date
                    ) ?: false
                )
            }
    }

    override suspend fun getTeacherLessons(studentId: Int) {

    }

}