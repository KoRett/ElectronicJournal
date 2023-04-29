package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.room.dao.TeacherDao
import com.gajeks.electronicjournal.domain.repository.TeacherRepository

class TeacherRepositoryImpl(private val teacherDao: TeacherDao) : TeacherRepository {

    override suspend fun getId(email: String): Int? = teacherDao.getId(email = email)

    override suspend fun getPassword(email: String): String? = teacherDao.getPassword(email = email)

}