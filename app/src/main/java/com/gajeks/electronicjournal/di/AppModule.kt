package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase
import com.gajeks.electronicjournal.presentation.entrance.login.LoginViewModel
import com.gajeks.electronicjournal.presentation.splash.SplashViewModel
import com.gajeks.electronicjournal.presentation.tabs.profile.ProfileViewModel
import com.gajeks.electronicjournal.presentation.tabs.schedule.ScheduleViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideLoginViewModelFactory(loginUseCase: LoginUseCase): LoginViewModel.Factory {
        return LoginViewModel.Factory(loginUseCase = loginUseCase)
    }

    @Provides
    fun provideSplashViewModelFactory(checkAccountLoginUseCase: CheckAccountLoginUseCase): SplashViewModel.Factory {
        return SplashViewModel.Factory(checkAccountLoginUseCase = checkAccountLoginUseCase)
    }

    @Provides
    fun provideProfileViewModelFactory(): ProfileViewModel.Factory{
        return ProfileViewModel.Factory()
    }

    @Provides
    fun provideScheduleViewModelFactory(): ScheduleViewModel.Factory{
        return ScheduleViewModel.Factory()
    }
}