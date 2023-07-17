package com.example.fitnesskit
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}