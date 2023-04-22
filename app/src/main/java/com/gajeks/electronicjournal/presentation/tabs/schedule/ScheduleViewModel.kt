package com.gajeks.electronicjournal.presentation.tabs.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gajeks.electronicjournal.models.BaseViewModel

class ScheduleViewModel : BaseViewModel() {

    class Factory(
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ScheduleViewModel() as T
        }
    }
}