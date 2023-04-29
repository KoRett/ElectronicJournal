package com.gajeks.electronicjournal.di

import android.content.Context
import androidx.room.Room
import com.gajeks.electronicjournal.data.repository.LocalAccountRepositoryImpl
import com.gajeks.electronicjournal.data.repository.StudentRepositoryImpl
import com.gajeks.electronicjournal.data.repository.TeacherRepositoryImpl
import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.data.storage.room.LocalDatabase
import com.gajeks.electronicjournal.data.storage.SharedPrefUserStorage
import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository
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
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideSharedPrefUserStorage(context: Context): LocalUserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideStudentRepository(localDatabase: LocalDatabase): StudentRepository {
        return StudentRepositoryImpl(studentDao = localDatabase.studentDao())
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

}