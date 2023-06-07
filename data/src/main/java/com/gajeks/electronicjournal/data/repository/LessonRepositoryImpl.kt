package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.models.StudentAttendanceInDB
import com.gajeks.electronicjournal.data.storage.room.dao.*
import com.gajeks.electronicjournal.data.storage.room.entities.AttendanceEntity
import com.gajeks.electronicjournal.domain.models.StudentAttendance
import com.gajeks.electronicjournal.domain.models.StudentLesson
import com.gajeks.electronicjournal.domain.models.StudentOfLesson
import com.gajeks.electronicjournal.domain.models.TeacherLesson
import com.gajeks.electronicjournal.domain.repository.LessonRepository

class LessonRepositoryImpl(
    private val studentDao: StudentDao,
    private val groupDao: GroupDao,
    private val lessonDao: LessonDao,
    private val lessonNameDao: LessonNameDao,
    private val teacherDao: TeacherDao,
    private val attendanceDao: AttendanceDao
) : LessonRepository {

    override suspend fun getStudentLessons(studentId: Int, date: String): List<StudentLesson>? {
        return groupDao.getLessons(studentDao.getGroupId(studentId = studentId)!!)
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
                    ) ?: false,
                    lessonId = lesson.lessonId
                )
            }
    }

    override suspend fun getTeacherLessons(teacherId: Int): List<TeacherLesson>? {
        return lessonDao.getTeacherLessons(teacherId)?.map { lessonWithGroups ->
            TeacherLesson(
                weekday = lessonWithGroups.lesson.weekday,
                isEvenWeek = lessonWithGroups.lesson.isEvenWeek,
                lessonNumber = lessonWithGroups.lesson.lessonNumber,
                isSeminar = lessonWithGroups.lesson.isSeminar,
                lessonName = lessonNameDao.getName(lessonNameId = lessonWithGroups.lesson.lessonNameId),
                groupsName = lessonWithGroups.groups.map { it.name },
                lessonId = lessonWithGroups.lesson.lessonId
            )
        }
    }

    override suspend fun getStudentsByLesson(lessonId: Int, date: String): List<StudentOfLesson>? {
        val groups = lessonDao.getLessonWithGroups(lessonId)?.groups?.map { it.groupId }
        val students: MutableList<StudentOfLesson> = mutableListOf()
        groups?.forEach { groupId ->
            students += studentDao.getStudents(groupId)
                ?.map {
                    StudentOfLesson(
                        studentId = it.studentId,
                        it.personNameInDB.map(),
                        attendanceDao.getAttendance(
                            studentId = it.studentId,
                            lessonId = lessonId,
                            date = date
                        ) ?: false
                    )
                }!!
        }
        return if (students.size > 0)
            students.toList()
        else
            null
    }

    override suspend fun saveStudentAttendanceByLesson(studentAttendance: StudentAttendance) {
        val attendances = attendanceDao.getAttendance(studentId = studentAttendance.studentId)
        var attendanceEntity: AttendanceEntity? = null
        attendances?.forEach {
            if ((it.lessonId == studentAttendance.lessonId) and (it.date == studentAttendance.date)) {
                attendanceEntity = it
                return@forEach
            }
        }
        if (attendanceEntity == null)
            attendanceDao.addAttendance(
                StudentAttendanceInDB(
                    studentId = studentAttendance.studentId,
                    lessonId = studentAttendance.lessonId,
                    date = studentAttendance.date,
                    isAttended = studentAttendance.isAttended
                )
            )
        else {
            attendanceEntity!!.isAttended = studentAttendance.isAttended
            attendanceDao.addAttendance(attendanceEntity!!)
        }
    }

}