package com.gmail.harsh_chuck.di

import android.app.Application
import com.gmail.harsh_chuck.domain.ReleaseTree
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ReleaseTreeModule {

    @Singleton
    @Provides
    fun bindReleaseTree(): ReleaseTree {
        return ReleaseTree()
    }
}

@InstallIn(ApplicationComponent::class)
@Module
object DebugTreeModule {

    @Singleton
    @Provides
    fun bindDebugTree(): Timber.DebugTree {
        return Timber.DebugTree()
    }
}