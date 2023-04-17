package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.domain.usecase.LoginUseCase
import com.gajeks.electronicjournal.presentation.login.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideLoginViewModelFactory(loginUseCase: LoginUseCase): LoginViewModel.Factory {
        return LoginViewModel.Factory(loginUseCase = loginUseCase)
    }

}