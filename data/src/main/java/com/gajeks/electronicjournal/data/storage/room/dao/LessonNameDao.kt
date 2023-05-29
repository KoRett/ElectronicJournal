package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gajeks.electronicjournal.data.storage.room.entities.LessonNameEntity

@Dao
interface LessonNameDao {

    @Query("SELECT name FROM ${LessonNameEntity.TABLE_NAME} WHERE lesson_name_id LIKE :lessonNameId")
    suspend fun getName(lessonNameId: Int): String

}