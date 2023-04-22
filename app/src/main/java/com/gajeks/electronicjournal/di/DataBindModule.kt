package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.data.storage.LocalUserStorage
import com.gajeks.electronicjournal.data.storage.UserStorageDatabase
import com.gajeks.electronicjournal.data.storage.RoomUserStorage
import com.gajeks.electronicjournal.data.storage.SharedPrefUserStorage
import dagger.Binds
import dagger.Module

@Module
interface DataBindModule {

    @Binds
    fun bindRoomUserStorage_to_UserStrorageDatabase(roomUserStorage: RoomUserStorage): UserStorageDatabase

    @Binds
    fun bindSharedPrefUserStorage_to_LocalUserStorage(sharedPrefUserStorage: SharedPrefUserStorage): LocalUserStorage

}