package com.gajeks.electronicjournal.data.storage

import android.content.Context
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase.Companion.DEFAULT_ID
import com.gajeks.electronicjournal.domain.usecase.CheckAccountLoginUseCase.Companion.DEFAULT_TYPE

class SharedPrefUserStorage(context: Context) : LocalUserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    override suspend fun getId(): Int {
        return sharedPreferences.getInt(KEY_ID, DEFAULT_ID)
    }

    override suspend fun saveId(id: Int) {
        sharedPreferences.edit().putInt(KEY_ID, id).apply()
    }

    override suspend fun removeId() {
        sharedPreferences.edit().remove(KEY_ID).apply()
    }

    override suspend fun getType(): String {
        return sharedPreferences.getString(KEY_TYPE, DEFAULT_TYPE) ?: DEFAULT_TYPE
    }

    override suspend fun saveType(type: String){
        sharedPreferences.edit().putString(KEY_TYPE, type).apply()
    }

    override suspend fun removeType() {
        sharedPreferences.edit().remove(KEY_TYPE).apply()
    }

    companion object{
        private const val SHARED_PREF_NAME = "shared_prefs_name"
        private const val KEY_ID = "id"
        private const val KEY_TYPE = "type"
    }

}