package com.gajeks.electronicjournal.di

import android.content.Context
import com.gajeks.electronicjournal.presentation.entrance.login.LoginFragment
import com.gajeks.electronicjournal.presentation.splash.SplashFragment
import com.gajeks.electronicjournal.presentation.tabs.profile.ProfileFragment
import com.gajeks.electronicjournal.presentation.tabs.schedule.ScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
@Singleton
interface AppComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(splashFragment: SplashFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(scheduleFragment: ScheduleFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}