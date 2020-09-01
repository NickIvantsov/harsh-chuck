package com.gmail.harsh_chuck.di

import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.AppNavigatorImpl
import com.gmail.harsh_chuck.app.navigator.JokeNavigator
import com.gmail.harsh_chuck.app.navigator.JokeNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator

    @Binds
    abstract fun bindJokeNavigator(impl: JokeNavigatorImpl): JokeNavigator
}