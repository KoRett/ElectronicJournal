package com.gajeks.electronicjournal.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gajeks.electronicjournal.data.storage.room.dao.StudentDao
import com.gajeks.electronicjournal.data.storage.room.dao.TeacherDao
import com.gajeks.electronicjournal.data.storage.room.entities.*

@Database(
    entities = [
        StudentEntity::class,
        TeacherEntity::class,
        GroupEntity::class,
        LessonEntity::class,
        LessonNameEntity::class,
        LessonsOfGroupEntity::class,
        LessonsOfTeacherEntity::class,
        AttendanceEntity::class,
        ExerciseEntity::class,
        EstimationEntity::class,
        TaskEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    abstract fun teacherDao(): TeacherDao
}