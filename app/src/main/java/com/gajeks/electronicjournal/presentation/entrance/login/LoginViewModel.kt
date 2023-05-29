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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val resultLiveData = MutableLiveData<String?>()
    val resultLive: LiveData<String?> = resultLiveData

    private var loginJob: Job? = null

    fun login(userLoginParams: UserLoginParams) {
        loginJob?.cancel()
        loginJob = viewModelScope.launch(Dispatchers.IO) {
            val result: String? = loginUseCase.execute(userLoginParams = userLoginParams)
            launch(Dispatchers.Main) {
                resultLiveData.value = result
            }
        }
    }

    class Factory @Inject constructor(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(loginUseCase = loginUseCase) as T
        }
    }
}