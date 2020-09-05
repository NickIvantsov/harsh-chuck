package com.gmail.harsh_chuck.helpers.debugProductionManager

import com.gmail.harsh_chuck.BuildConfig
import com.gmail.harsh_chuck.helpers.debugProductionManager.dubugImpl.DebugType
import com.gmail.harsh_chuck.helpers.debugProductionManager.productionImpl.ProductionType
import com.gmail.harsh_chuck.network.TypeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class DebugProductionManager @Inject constructor(val interceptor: HttpLoggingInterceptor) {
    protected var typeVersion: TypeVersion = identifyBuildType()
    protected fun identifyBuildType() =
        if (BuildConfig.DEBUG) DebugType(interceptor) else ProductionType()

    fun getVersionTypes(): VersionTypes = typeVersion.getVersionTypes()
    fun getOkHttpClientBuilder(): OkHttpClient.Builder = typeVersion.getOkHttpClientBuilder()
    fun getOkHttpClientBuilder(type: TypeApi): OkHttpClient.Builder =
        typeVersion.getOkHttpClientBuilder(type)
}