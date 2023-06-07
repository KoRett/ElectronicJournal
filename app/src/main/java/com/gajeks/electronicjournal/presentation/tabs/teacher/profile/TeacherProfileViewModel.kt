package com.gajeks.electronicjournal.presentation.tabs.teacher.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gajeks.electronicjournal.domain.models.*
import com.gajeks.electronicjournal.domain.usecase.GetTeacherDataUseCase
import com.gajeks.electronicjournal.domain.usecase.LogoutUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeacherProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getTeacherDataUseCase: GetTeacherDataUseCase
) : BaseViewModel() {

    private var teacherData = MutableLiveResult<TeacherData>(PendingResult())
    var teacher: LiveResult<TeacherData> = teacherData

    fun getTeacherData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getTeacherDataUseCase.exec(UserParams.id!!)
                teacherData.postValue(SuccessResult(data))
            } catch (_: Exception) {
                teacherData.postValue(ErrorResult(RuntimeException()))
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase.execute()
        }
    }

    class Factory @Inject constructor(
        private val logoutUseCase: LogoutUseCase,
        private val getTeacherDataUseCase: GetTeacherDataUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TeacherProfileViewModel(logoutUseCase, getTeacherDataUseCase) as T
        }
    }

}