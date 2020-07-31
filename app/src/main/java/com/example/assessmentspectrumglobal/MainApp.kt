package com.example.assessmentspectrumglobal

import android.app.Application
import com.example.assessmentspectrumglobal.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Created by kiranb on 30/7/20
 */
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidLogger()
        androidContext(this@MainApp)
        modules(appComponent)
    }
}