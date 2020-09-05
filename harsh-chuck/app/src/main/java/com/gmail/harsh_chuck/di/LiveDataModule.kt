package com.gmail.harsh_chuck.di

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object LiveDataModule {
    @Provides
    fun provideJokeLiveData(): MutableLiveData<JokeRandomResponse> {
        return MutableLiveData<JokeRandomResponse>()
    }
}