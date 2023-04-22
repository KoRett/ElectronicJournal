package com.gajeks.electronicjournal.presentation.tabs.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gajeks.electronicjournal.models.BaseViewModel

class ProfileViewModel : BaseViewModel() {

    class Factory(
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel() as T
        }
    }
}