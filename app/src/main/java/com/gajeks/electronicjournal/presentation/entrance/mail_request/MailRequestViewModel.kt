package com.gajeks.electronicjournal.presentation.entrance.mail_request

import androidx.lifecycle.*
import com.gajeks.electronicjournal.domain.models.ErrorResult
import com.gajeks.electronicjournal.domain.models.NothingResult
import com.gajeks.electronicjournal.domain.models.PendingResult
import com.gajeks.electronicjournal.domain.models.SuccessResult
import com.gajeks.electronicjournal.domain.usecase.ConfirmEmailUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MailRequestViewModel(
    private val confirmEmailUseCase: ConfirmEmailUseCase
) : BaseViewModel() {

    private val resultLiveData = MutableLiveResult<Unit>()
    val resultLive: LiveResult<Unit> = resultLiveData

    private var job: Job? = null

    init {
        resultLiveData.value = NothingResult()
    }

    fun sendEmail(emailReceiver: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                resultLiveData.value = PendingResult()
            }
            delay(1000L)
            val result = confirmEmailUseCase.exec(emailReceiver = emailReceiver)

            launch(Dispatchers.Main) {
                if (result)
                    resultLiveData.value = SuccessResult(Unit)
                else
                    resultLiveData.value = ErrorResult(RuntimeException())
            }
        }
    }

    fun resetResult() {
        resultLiveData.value = NothingResult()
    }

    class Factory @Inject constructor(
        private val confirmEmailUseCase: ConfirmEmailUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MailRequestViewModel(
                confirmEmailUseCase = confirmEmailUseCase
            ) as T
        }
    }
}