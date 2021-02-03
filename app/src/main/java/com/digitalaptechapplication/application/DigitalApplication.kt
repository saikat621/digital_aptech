package com.digitalaptechapplication.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.digitalaptechapplication.BuildConfig
import com.digitalaptechapplication.database.AppDatabase
import com.digitalaptechapplication.di.repositoryModule
import com.digitalaptechapplication.di.roomModule
import com.digitalaptechapplication.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigitalApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@DigitalApplication)
            Stetho.initializeWithDefaults(this@DigitalApplication)
            modules(listOf(roomModule, viewModelModule, repositoryModule))
        }
    }
}