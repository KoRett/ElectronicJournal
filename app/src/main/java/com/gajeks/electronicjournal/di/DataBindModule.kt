package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.data.storage.UserStorage
import com.gajeks.electronicjournal.data.storage.UserStorageSQL
import dagger.Binds
import dagger.Module

@Module
interface DataBindModule {

    @Binds
    fun bindUserStorageSQL_to_UserStorage(userStorageSQL: UserStorageSQL): UserStorage
}