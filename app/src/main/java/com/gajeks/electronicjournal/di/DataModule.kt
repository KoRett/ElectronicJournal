package com.gajeks.electronicjournal.di

import android.content.Context
import com.gajeks.electronicjournal.data.repository.UserRepositoryImpl
import com.gajeks.electronicjournal.data.storage.UserStorage
import com.gajeks.electronicjournal.data.storage.UserStorageSQL
import com.gajeks.electronicjournal.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBindModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideUserStorage(context: Context): UserStorageSQL {
        return UserStorageSQL(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository{
        return UserRepositoryImpl(userStorage = userStorage)
    }

}