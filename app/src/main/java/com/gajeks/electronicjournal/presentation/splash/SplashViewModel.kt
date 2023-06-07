package com.gajeks.electronicjournal.presentation.splash

import androidx.lifecycle.*
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel(private val checkAccountLoginUseCase: CheckAccountLoginUseCase) :
    BaseViewModel() {

    private val resultLiveData = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveData

    init {
        checkAccountLogin()
    }

    private fun checkAccountLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = checkAccountLoginUseCase.execute()
            resultLiveData.postValue(result)
        }
    }

    class Factory @Inject constructor(
        private val checkAccountLoginUseCase: CheckAccountLoginUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SplashViewModel(checkAccountLoginUseCase = checkAccountLoginUseCase) as T
        }
    }
}