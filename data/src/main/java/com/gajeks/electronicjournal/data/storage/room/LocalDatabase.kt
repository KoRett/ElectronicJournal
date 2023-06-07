package com.gajeks.electronicjournal.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gajeks.electronicjournal.data.storage.room.dao.*
import com.gajeks.electronicjournal.data.storage.room.entities.*

@Database(
    entities = [
        StudentEntity::class,
        TeacherEntity::class,
        GroupEntity::class,
        LessonEntity::class,
        LessonNameEntity::class,
        LessonsOfGroupEntity::class,
        AttendanceEntity::class,
        ExerciseEntity::class,
        EstimationEntity::class,
        TaskEntity::class
    ],
    version = 20,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun groupDao(): GroupDao
    abstract fun lessonNameDao(): LessonNameDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun lessonDao(): LessonDao

}