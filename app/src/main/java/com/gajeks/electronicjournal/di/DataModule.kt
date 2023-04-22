package com.gajeks.electronicjournal.di

import android.content.Context
import androidx.room.Room
import com.gajeks.electronicjournal.data.repository.UserRepositoryImpl
import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.data.storage.room.LocalDatabase
import com.gajeks.electronicjournal.data.storage.UserStorageDatabase
import com.gajeks.electronicjournal.data.storage.RoomUserStorage
import com.gajeks.electronicjournal.data.storage.SharedPrefUserStorage
import com.gajeks.electronicjournal.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBindModule::class])
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
    fun provideRoomUserStorage(localDatabase: LocalDatabase): RoomUserStorage {
        return RoomUserStorage(userDao = localDatabase.userDao())
    }

    @Provides
    @Singleton
    fun provideSharedPrefUserStorage(context: Context): SharedPrefUserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorageDatabase: UserStorageDatabase, localUserStorage: LocalUserStorage): UserRepository {
        return UserRepositoryImpl(userStorageDatabase = userStorageDatabase, localUserStorage = localUserStorage)
    }

}