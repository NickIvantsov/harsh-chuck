package com.gmail.harsh_chuck.domain

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.facebook.stetho.Stetho
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
        if (BuildConfig.DEBUG) {
            setTimberDebugTree()

            // Create an InitializerBuilder
            // Create an InitializerBuilder
            val initializerBuilder = Stetho.newInitializerBuilder(this)

            // Enable Chrome DevTools

            // Enable Chrome DevTools
            initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
            )

            // Enable command line interface

            // Enable command line interface
            initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
            )

            // Use the InitializerBuilder to generate an Initializer

            // Use the InitializerBuilder to generate an Initializer
            val initializer = initializerBuilder.build()

            // Initialize Stetho with the Initializer

            // Initialize Stetho with the Initializer
            Stetho.initialize(initializer)
        } else
            timberReleaseTreeInit()

    }

    private fun setTimberDebugTree() {
        Timber.plant(debugTree)
    }

    private fun timberReleaseTreeInit() {
        Timber.plant(releaseTree)
    }
}