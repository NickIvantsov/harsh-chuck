package com.gmail.harsh_chuck.di

import com.gmail.harsh_chuck.network.request.ICategoriesJokes
import com.gmail.harsh_chuck.network.request.IJokeByCategory
import com.gmail.harsh_chuck.network.request.IRandomJokes
import com.gmail.harsh_chuck.network.request.requestImpl.JokesCategoriesRequestImpl
import com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl.RandomJokesByCategoryRequestImpl
import com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl.RandomJokesRequestImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
abstract class RandomJokesRequestModule {

    @ActivityScoped
    @Binds
    abstract fun bindRandomJokesRequest(impl: RandomJokesRequestImpl): IRandomJokes

    @ActivityScoped
    @Binds
    abstract fun bindJokesCategoriesRequest(impl: JokesCategoriesRequestImpl): ICategoriesJokes

    @ActivityScoped
    @Binds
    abstract fun bindJokesByCategoryRequest(impl: RandomJokesByCategoryRequestImpl): IJokeByCategory
}