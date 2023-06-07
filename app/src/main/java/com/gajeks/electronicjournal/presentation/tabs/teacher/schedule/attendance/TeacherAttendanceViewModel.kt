package com.gajeks.electronicjournal.presentation.tabs.teacher.schedule.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gajeks.electronicjournal.domain.models.*
import com.gajeks.electronicjournal.domain.usecase.GetStudentsByLessonUseCase
import com.gajeks.electronicjournal.domain.usecase.SaveStudentAttendanceUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.InvalidParameterException
import javax.inject.Inject

class TeacherAttendanceViewModel(
    private val getStudentsByLessonUseCase: GetStudentsByLessonUseCase,
    private val saveStudentAttendanceUseCase: SaveStudentAttendanceUseCase
) : BaseViewModel() {

    private var studentsData = MutableLiveResult<List<StudentOfLesson>>(PendingResult())
    var students: LiveResult<List<StudentOfLesson>> = studentsData

    private var saveAttendanceData = MutableLiveResult<Unit>(NothingResult())
    var saveAttendance: LiveResult<Unit> = saveAttendanceData


    private var studentsAttendance: MutableMap<Int, Boolean> = mutableMapOf()

    private var jobGetStudents: Job? = null
    private var jobSaveStudents: Job? = null

    fun saveStudentAttendance(lessonId: Int, date: String) {
        jobSaveStudents?.cancel()
        jobSaveStudents = viewModelScope.launch(Dispatchers.IO) {
            saveAttendanceData.postValue(PendingResult())
            delay(200L)
            try {
                studentsAttendance.forEach {
                    saveStudentAttendanceUseCase.exec(
                        StudentAttendance(
                            it.key,
                            lessonId,
                            date,
                            it.value
                        )
                    )
                }
                saveAttendanceData.postValue(SuccessResult(Unit))
            } catch (e: Exception) {
                saveAttendanceData.postValue(ErrorResult(RuntimeException()))
            }
        }
    }

    fun setStudentAttendance(studentId: Int, isAttended: Boolean) {
        studentsAttendance[studentId] = isAttended
    }

    fun getStudents(lessonId: Int, date: String) {
        jobGetStudents?.cancel()
        jobGetStudents = viewModelScope.launch(Dispatchers.IO) {
            studentsData.postValue(PendingResult())
            val students = getStudentsByLessonUseCase.exec(lessonId, date)
            if (students == null)
                studentsData.postValue(ErrorResult(InvalidParameterException()))
            else {
                students.forEach {
                    studentsAttendance[it.studentId] = it.isAttended
                }
                studentsData.postValue(SuccessResult(students))
            }
        }
    }

    class Factory @Inject constructor(
        private val getStudentsByLessonUseCase: GetStudentsByLessonUseCase,
        private val saveStudentAttendanceUseCase: SaveStudentAttendanceUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TeacherAttendanceViewModel(
                getStudentsByLessonUseCase,
                saveStudentAttendanceUseCase
            ) as T
        }
    }
}