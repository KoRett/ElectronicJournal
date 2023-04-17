package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.domain.repository.UserRepository
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides


@Module(includes = [DataModule::class])
class DomainModule {

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository): LoginUseCase {
        return LoginUseCase(userRepository = userRepository)
    }

}