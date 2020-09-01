package com.gmail.harsh_chuck.di

import android.content.Context
import com.gmail.harsh_chuck.app.adapters.CategoriesJokesAdapter
import com.gmail.harsh_chuck.app.adapters.RadioAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ActivityComponent::class)
@Module
class JokesCategoriesAdapterModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): RadioAdapter<String> {
        return CategoriesJokesAdapter(appContext)
    }

}