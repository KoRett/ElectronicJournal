package com.gajeks.electronicjournal.presentation.entrance.changing_password

import androidx.lifecycle.*
import com.gajeks.electronicjournal.domain.models.ErrorResult
import com.gajeks.electronicjournal.domain.models.NothingResult
import com.gajeks.electronicjournal.domain.models.PendingResult
import com.gajeks.electronicjournal.domain.models.SuccessResult
import com.gajeks.electronicjournal.domain.usecase.ChangePasswordUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangingPasswordViewModel(private val changingPasswordUseCase: ChangePasswordUseCase) :
    BaseViewModel() {

    private val resultLiveData = MutableLiveResult<Unit>()
    val resultLive: LiveResult<Unit> = resultLiveData

    private var job: Job? = null

    init {
        resultLiveData.value = NothingResult()
    }

    fun changePassword(
        code: Int,
        newPassword: String,
        confirmNewPassword: String
    ) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                resultLiveData.value = PendingResult()
            }
            try {
                changingPasswordUseCase.exec(
                    code = code,
                    newPassword = newPassword,
                    confirmNewPassword = confirmNewPassword
                )
                launch(Dispatchers.Main) {
                    resultLiveData.value = SuccessResult(Unit)
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    resultLiveData.value = ErrorResult(e)
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val changingPasswordUseCase: ChangePasswordUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChangingPasswordViewModel(
                changingPasswordUseCase = changingPasswordUseCase
            ) as T
        }
    }
}