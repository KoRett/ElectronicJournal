package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.gajeks.electronicjournal.data.storage.room.entities.LessonEntity
import com.gajeks.electronicjournal.data.storage.room.entities.relationships.LessonWithGroups

@Dao
interface LessonDao {

    @Transaction
    @Query("SELECT * FROM ${LessonEntity.TABLE_NAME} WHERE teacher_id LIKE :teacherId")
    suspend fun getTeacherLessons(teacherId: Int): List<LessonWithGroups>?

    @Transaction
    @Query("SELECT * FROM ${LessonEntity.TABLE_NAME} WHERE lesson_id LIKE :lessonId")
    suspend fun getLessonWithGroups(lessonId: Int): LessonWithGroups?

}