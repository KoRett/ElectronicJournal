package com.gajeks.electronicjournal.di

import android.content.Context
import androidx.room.Room
import com.gajeks.electronicjournal.data.repository.*
import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.data.storage.room.LocalDatabase
import com.gajeks.electronicjournal.data.storage.SharedPrefUserStorage
import com.gajeks.electronicjournal.domain.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            name = "local_database.db"
        ).createFromAsset("database/local_database.db").build()

    @Provides
    @Singleton
    fun provideSharedPrefUserStorage(context: Context): LocalUserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideStudentRepository(localDatabase: LocalDatabase): StudentRepository {
        return StudentRepositoryImpl(
            studentDao = localDatabase.studentDao(),
            groupDao = localDatabase.groupDao(),
            attendanceDao = localDatabase.attendanceDao()
        )
    }

    @Provides
    @Singleton
    fun provideTeacherRepository(localDatabase: LocalDatabase): TeacherRepository {
        return TeacherRepositoryImpl(teacherDao = localDatabase.teacherDao())
    }

    @Provides
    @Singleton
    fun provideLocalAccountRepository(localUserStorage: LocalUserStorage): LocalAccountRepository {
        return LocalAccountRepositoryImpl(localUserStorage = localUserStorage)
    }

    @Provides
    @Singleton
    fun provideIncomingUserRepository(): IncomingUserRepository {
        return IncomingUserRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLessonRepository(localDatabase: LocalDatabase): LessonRepository {
        return LessonRepositoryImpl(
            studentDao = localDatabase.studentDao(),
            groupDao = localDatabase.groupDao(),
            lessonDao = localDatabase.lessonDao(),
            lessonNameDao = localDatabase.lessonNameDao(),
            teacherDao = localDatabase.teacherDao(),
            attendanceDao = localDatabase.attendanceDao()
        )
    }

}