package com.gajeks.electronicjournal.presentation.tabs.student.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gajeks.electronicjournal.domain.models.*
import com.gajeks.electronicjournal.domain.usecase.GetStudentDataUseCase
import com.gajeks.electronicjournal.domain.usecase.LogoutUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudentProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getStudentDataUseCase: GetStudentDataUseCase
) : BaseViewModel() {

    private var studentData = MutableLiveResult<StudentData>(PendingResult())
    var student: LiveResult<StudentData> = studentData

    fun getStudentData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getStudentDataUseCase.exec(UserParams.id!!)
                launch(Dispatchers.Main) {
                    studentData.value = SuccessResult(data)
                }
            } catch (_: Exception) {
                launch(Dispatchers.Main) {
                    studentData.value = ErrorResult(RuntimeException())
                }
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
        private val getStudentDataUseCase: GetStudentDataUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StudentProfileViewModel(logoutUseCase, getStudentDataUseCase) as T
        }
    }
}