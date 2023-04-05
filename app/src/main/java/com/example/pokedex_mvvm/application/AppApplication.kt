package com.example.pokedex_mvvm.application

import android.app.Application
import com.example.pokedex_mvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(listOf(dataSourceModule, retrofitModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}

