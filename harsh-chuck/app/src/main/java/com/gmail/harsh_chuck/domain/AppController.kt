package com.gmail.harsh_chuck.domain

import android.app.Application
import com.gmail.harsh_chuck.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppController : Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            setTimberDebugTree()
        else
            timberReleaseTreeInit()

    }

    private fun setTimberDebugTree() {
        Timber.plant(Timber.DebugTree())
    }

    private fun timberReleaseTreeInit() {
        Timber.plant(ReleaseTree(this))
    }
}