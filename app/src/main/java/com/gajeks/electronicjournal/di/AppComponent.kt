package com.gajeks.electronicjournal.di

import android.content.Context
import com.gajeks.electronicjournal.presentation.entrance.changing_password.ChangingPasswordFragment
import com.gajeks.electronicjournal.presentation.entrance.login.LoginFragment
import com.gajeks.electronicjournal.presentation.entrance.mail_request.MailRequestFragment
import com.gajeks.electronicjournal.presentation.splash.SplashFragment
import com.gajeks.electronicjournal.presentation.tabs.student.StudentTabsFragment
import com.gajeks.electronicjournal.presentation.tabs.student.profile.StudentProfileFragment
import com.gajeks.electronicjournal.presentation.tabs.student.schedule.StudentScheduleFragment
import com.gajeks.electronicjournal.presentation.tabs.view_pager.ViewPagerItem
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
@Singleton
interface AppComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(splashFragment: SplashFragment)
    fun inject(studentProfileFragment: StudentProfileFragment)
    fun inject(studentScheduleFragment: StudentScheduleFragment)
    fun inject(mailRequestFragment: MailRequestFragment)
    fun inject(changingPasswordFragment: ChangingPasswordFragment)
    fun inject(viewPagerItem: ViewPagerItem)
    fun inject(studentTabsFragment: StudentTabsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}