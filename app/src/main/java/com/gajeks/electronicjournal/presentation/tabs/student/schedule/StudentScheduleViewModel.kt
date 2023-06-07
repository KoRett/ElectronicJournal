package com.gajeks.electronicjournal.presentation.tabs.student.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gajeks.electronicjournal.domain.models.*
import com.gajeks.electronicjournal.domain.models.selected_date.SelectedDate
import com.gajeks.electronicjournal.domain.models.selected_date.WeekdayObserver
import com.gajeks.electronicjournal.domain.usecase.GetStudentLessonsUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.security.InvalidParameterException
import javax.inject.Inject

class StudentScheduleViewModel(
    private val getStudentLessonsUseCase: GetStudentLessonsUseCase,
    private val selectedDate: SelectedDate
) : BaseViewModel(), WeekdayObserver {

    private var lessonsData = MutableLiveResult<List<StudentLesson>>(PendingResult())
    var lessons: LiveResult<List<StudentLesson>> = lessonsData

    private var job: Job? = null

    init {
        selectedDate.addWeekdayObserver(this)
    }

    override fun onWeekdaySelection() {
        getSchedule()
    }

    fun getSchedule() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            lessonsData.postValue(PendingResult())
            val lessons = getStudentLessonsUseCase.execute(
                studentId = UserParams.id!!,
                isEvenWeek = selectedDate.selectedWeek!! % 2 == 0,
                weekday = selectedDate.selectedWeekday!!,
                date = selectedDate.date!!,
            )
            if (lessons == null)
                lessonsData.postValue(ErrorResult(InvalidParameterException()))
            else
                lessonsData.postValue(SuccessResult(lessons))
        }
    }

    class Factory @Inject constructor(
        private val getStudentLessonsUseCase: GetStudentLessonsUseCase,
        private val selectedDate: SelectedDate
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StudentScheduleViewModel(getStudentLessonsUseCase, selectedDate) as T
        }
    }

    override fun onCleared() {
        super.onCleared()
        selectedDate.removeWeekdayObserver(this)
    }

}