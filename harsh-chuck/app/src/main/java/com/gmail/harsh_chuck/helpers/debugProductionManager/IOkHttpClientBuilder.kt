package com.gmail.harsh_chuck.helpers.debugProductionManager

import okhttp3.OkHttpClient

interface IOkHttpClientBuilder:IOkHttpBuilder {
    fun getOkHttpClientBuilder(): OkHttpClient.Builder
}