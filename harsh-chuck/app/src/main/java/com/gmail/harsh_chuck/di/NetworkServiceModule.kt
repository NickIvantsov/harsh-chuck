package com.gmail.harsh_chuck.di

import com.gmail.harsh_chuck.network.INetworkService
import com.gmail.harsh_chuck.network.NetworkService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class NetworkServiceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkService(impl: NetworkService): INetworkService


}
