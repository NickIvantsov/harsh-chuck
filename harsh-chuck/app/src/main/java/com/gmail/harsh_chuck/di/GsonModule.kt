package com.gmail.harsh_chuck.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class GsonModule {
    @Singleton
    @Provides
    fun bindGson(): Gson {
        return Gson()
    }
}