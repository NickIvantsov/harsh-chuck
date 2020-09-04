package com.gmail.harsh_chuck.domain

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class AppController : Application() {

    /**
     * liveData предназначаеться для определения активности кнопки OK при отображении категорий
     */
    val jokeCategoryLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    /**
     * liveData служит для передачи данных между фрагментами
     */
    val stringData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    @Inject
    lateinit var releaseTree: ReleaseTree

    @Inject
    lateinit var debugTree: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            setTimberDebugTree()
        else
            timberReleaseTreeInit()

    }

    private fun setTimberDebugTree() {
        Timber.plant(debugTree)
    }

    private fun timberReleaseTreeInit() {
        Timber.plant(releaseTree)
    }
}