package com.gajeks.electronicjournal.presentation.entrance.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gajeks.electronicjournal.domain.models.UserLoginParams
import com.gajeks.electronicjournal.domain.usecase.LoginUseCase
import com.gajeks.electronicjournal.models.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val resultLiveData = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = resultLiveData

    fun login(userLoginParams: UserLoginParams) {
        viewModelScope.launch(Dispatchers.IO) {
            resultLiveData.postValue(loginUseCase.execute(userLoginParams = userLoginParams))
        }
    }

    class Factory(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(loginUseCase = loginUseCase) as T
        }
    }
}