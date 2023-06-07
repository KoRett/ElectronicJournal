package com.gajeks.electronicjournal.di

import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
import com.gajeks.electronicjournal.domain.repository.*
import com.gajeks.electronicjournal.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


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

    @Provides
    fun provideGetStudentLessonsUseCase(
        lessonRepository: LessonRepository
    ): GetStudentLessonsUseCase {
        return GetStudentLessonsUseCase(lessonRepository = lessonRepository)
    }

    @Provides
    fun provideGetTeacherLessonsUseCase(
        lessonRepository: LessonRepository
    ): GetTeacherLessonsUseCase {
        return GetTeacherLessonsUseCase(lessonRepository = lessonRepository)
    }


    @Provides
    fun provideLogoutUseCase(
        localAccountRepository: LocalAccountRepository
    ): LogoutUseCase {
        return LogoutUseCase(localAccountRepository = localAccountRepository)
    }

    @Provides
    fun provideConfirmEmailUseCase(
        incomingUserRepository: IncomingUserRepository,
        teacherRepository: TeacherRepository,
        studentRepository: StudentRepository
    ): ConfirmEmailUseCase {
        return ConfirmEmailUseCase(
            incomingUserRepository = incomingUserRepository,
            teacherRepository = teacherRepository,
            studentRepository = studentRepository
        )
    }

    @Provides
    fun provideChangingPasswordUseCase(
        teacherRepository: TeacherRepository,
        studentRepository: StudentRepository
    ): ChangePasswordUseCase {
        return ChangePasswordUseCase(
            studentRepository = studentRepository,
            teacherRepository = teacherRepository
        )
    }

    @Provides
    fun provideGetStudentDataUseCase(
        studentRepository: StudentRepository
    ): GetStudentDataUseCase {
        return GetStudentDataUseCase(studentRepository = studentRepository)
    }

    @Provides
    fun provideGetTeacherDataUseCase(
        teacherRepository: TeacherRepository
    ): GetTeacherDataUseCase {
        return GetTeacherDataUseCase(teacherRepository = teacherRepository)
    }

    @Provides
    fun provideGetStudentsByLessonUseCase(
        lessonRepository: LessonRepository
    ): GetStudentsByLessonUseCase {
        return GetStudentsByLessonUseCase(lessonRepository = lessonRepository)
    }

    @Provides
    fun provideSaveStudentAttendanceUseCase(
        lessonRepository: LessonRepository
    ): SaveStudentAttendanceUseCase {
        return SaveStudentAttendanceUseCase(lessonRepository = lessonRepository)
    }

    @Provides
    @Singleton
    fun provideSelectedDate(): SelectedDate = SelectedDate()

}