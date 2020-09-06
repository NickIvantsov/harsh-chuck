package com.gmail.harsh_chuck.di

import android.content.Context
import com.gmail.harsh_chuck.app.adapters.CategoriesJokesAdapter
import com.gmail.harsh_chuck.app.adapters.JokesHistoryAdapter
import com.gmail.harsh_chuck.app.adapters.JokesHistoryAdapterImpl
import com.gmail.harsh_chuck.app.adapters.RadioAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped

@InstallIn(ActivityComponent::class)
@Module
class JokesCategoriesAdapterModule {

    @Provides
    fun provideJokesCategoriesAdapter(
        @ApplicationContext appContext: Context
    ): RadioAdapter<String> {
        return CategoriesJokesAdapter(appContext)
    }

}

@InstallIn(FragmentComponent::class)
@Module
class JokesCategoryHistoryAdapterModule {

    @FragmentScoped
    @Provides
    fun provideJokesCategoriesAdapter(
        @ApplicationContext appContext: Context
    ): JokesHistoryAdapter<String> {
        return JokesHistoryAdapterImpl(appContext)
    }

}