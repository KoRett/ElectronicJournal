package com.gajeks.electronicjournal.presentation.splash

import androidx.lifecycle.*
import com.gajeks.electronicjournal.domain.models.SuccessResult
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import com.gajeks.electronicjournal.models.LiveResult
import com.gajeks.electronicjournal.models.MutableLiveResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val checkAccountLoginUseCase: CheckAccountLoginUseCase) : BaseViewModel() {

    private val resultLiveData = MutableLiveResult<Boolean>()
    val resultLive: LiveResult<Boolean> = resultLiveData

    init{
        checkAccountLogin()
    }

    private fun checkAccountLogin(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: SuccessResult<Boolean> = checkAccountLoginUseCase.execute()
            launch(Dispatchers.Main) {
                resultLiveData.value = result
            }
        }
    }


    class Factory(
        private val checkAccountLoginUseCase: CheckAccountLoginUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SplashViewModel(checkAccountLoginUseCase = checkAccountLoginUseCase) as T
        }
    }
}