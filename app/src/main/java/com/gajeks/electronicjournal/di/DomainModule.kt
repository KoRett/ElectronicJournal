package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.domain.repository.LocalAccountRepository
import com.gajeks.electronicjournal.domain.repository.StudentRepository
import com.gajeks.electronicjournal.domain.repository.TeacherRepository
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides


@Module(includes = [DataModule::class])
class DomainModule {

    @Provides
    fun provideLoginUseCase(
        studentRepository: StudentRepository,
        teacherRepository: TeacherRepository,
        localAccountRepository: LocalAccountRepository
    ): LoginUseCase {
        return LoginUseCase(
            studentRepository = studentRepository,
            teacherRepository = teacherRepository,
            localAccountRepository = localAccountRepository
        )
    }

    @Provides
    fun provideCheckAccountLoginUseCase(
        localAccountRepository: LocalAccountRepository
    ): CheckAccountLoginUseCase {
        return CheckAccountLoginUseCase(localAccountRepository = localAccountRepository)
    }

}