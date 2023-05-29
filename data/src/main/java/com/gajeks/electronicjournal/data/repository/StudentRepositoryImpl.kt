package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.room.dao.AttendanceDao
import com.gajeks.electronicjournal.data.storage.room.dao.GroupDao
import com.gajeks.electronicjournal.data.storage.room.dao.StudentDao
import com.gajeks.electronicjournal.domain.repository.PersonName
import com.gajeks.electronicjournal.domain.repository.StudentRepository

class StudentRepositoryImpl(
    private val studentDao: StudentDao,
    private val groupDao: GroupDao,
    private val attendanceDao: AttendanceDao
) : StudentRepository {

    override suspend fun getId(email: String): Int? = studentDao.getId(email = email)

    override suspend fun getPassword(email: String): String? = studentDao.getPassword(email = email)

    override suspend fun setPassword(studentId: Int, newPassword: String) =
        studentDao.setPassword(studentId = studentId, newPassword = newPassword)

    override suspend fun getName(studentId: Int): PersonName? =
        studentDao.getName(studentId)?.map()

    override suspend fun getAttendance(studentId: Int): Int? =
        attendanceDao.getAttendance(studentId)?.size

    override suspend fun getGroup(studentId: Int): String? =
        groupDao.getName(studentDao.getGroupId(studentId)!!)

}