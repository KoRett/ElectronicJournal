package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.storage.room.dao.StudentDao
import com.gajeks.electronicjournal.domain.repository.StudentRepository

class StudentRepositoryImpl(private val studentDao: StudentDao) : StudentRepository {

    override suspend fun getId(email: String): Int? = studentDao.getId(email = email)

    override suspend fun getPassword(email: String): String? = studentDao.getPassword(email = email)

}