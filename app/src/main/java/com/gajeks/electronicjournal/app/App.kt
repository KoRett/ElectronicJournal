package com.gajeks.electronicjournal.app

import android.app.Application
import com.gajeks.electronicjournal.di.AppComponent
import com.gajeks.electronicjournal.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(context = this).build()
    }
}