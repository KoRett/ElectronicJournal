package com.gajeks.electronicjournal.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gajeks.electronicjournal.data.storage.room.entities.AttendanceEntity

@Dao
interface AttendanceDao {

    @Query("SELECT is_attended FROM ${AttendanceEntity.TABLE_NAME} WHERE date LIKE :date AND student_id LIKE :studentId AND lesson_id LIKE :lessonId")
    suspend fun getAttendance(studentId: Int, lessonId: Int, date: String): Boolean?

    @Query("SELECT * FROM ${AttendanceEntity.TABLE_NAME} WHERE student_id LIKE :studentId")
    suspend fun getAttendance(studentId: Int): List<AttendanceEntity>?

    @Insert
    suspend fun addAttendance(entity: AttendanceEntity)

}