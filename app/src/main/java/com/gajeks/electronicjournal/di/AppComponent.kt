package com.gajeks.electronicjournal.di

import android.content.Context
import com.gajeks.electronicjournal.presentation.login.LoginFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
@Singleton
interface AppComponent {

    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}