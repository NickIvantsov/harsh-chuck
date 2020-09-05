package com.gmail.harsh_chuck.di

import com.gmail.harsh_chuck.domain.repository.chuckImpl.ChuckRepository
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindChuckRepository(impl: ChuckRepository): IChuckRepository
}