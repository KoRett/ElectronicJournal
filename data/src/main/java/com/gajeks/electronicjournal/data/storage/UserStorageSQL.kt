package com.gajeks.electronicjournal.data.storage

import android.content.Context


class UserStorageSQL(context: Context) : UserStorage {

    private val MODE_PRIVATE = 0

    private val db by lazy(LazyThreadSafetyMode.NONE) {
        context.openOrCreateDatabase(
            "app.db",
            MODE_PRIVATE,
            null
        )
    }

    init {
        val command = "CREATE TABLE IF NOT EXISTS users(email TEXT PRIMARY KEY UNIQUE," +
                " password TEXT," +
                " firstName TEXT," +
                " lastName TEXT," +
                " patronymic TEXT," +
                " groupId TEXT)"
        db.execSQL(command)
    }



    override fun getPassword(email: String): String {
        return "1234"
    }
}