package com.gajeks.electronicjournal.data.storage

import android.content.Context

private const val SHARED_PREF_NAME = "shared_prefs_name"
private const val KEY_ID = "id"
private const val DEFAULT_ID = -1

class SharedPrefUserStorage(context: Context) : LocalUserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    override suspend fun getId(): Int {
        return sharedPreferences.getInt(KEY_ID, DEFAULT_ID)
    }

    override suspend fun saveId(id: Int) {
        sharedPreferences.edit().putInt(KEY_ID, id).apply()
    }


}