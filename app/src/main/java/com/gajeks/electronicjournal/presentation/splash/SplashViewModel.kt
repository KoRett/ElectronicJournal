package com.gajeks.electronicjournal.presentation.splash

import androidx.lifecycle.*
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val checkAccountLoginUseCase: CheckAccountLoginUseCase) : BaseViewModel() {

    private val resultLiveData = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = resultLiveData

    init{
        checkAccountLogin()
    }

    private fun checkAccountLogin(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            resultLiveData.postValue(checkAccountLoginUseCase.execute())
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