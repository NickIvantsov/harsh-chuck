package com.gmail.harsh_chuck.helpers.debugProductionManager

import com.gmail.harsh_chuck.network.TypeApi
import okhttp3.OkHttpClient

interface IOkHttpClientBuilderByType : IOkHttpBuilder {
    fun getOkHttpClientBuilder(type: TypeApi): OkHttpClient.Builder
}