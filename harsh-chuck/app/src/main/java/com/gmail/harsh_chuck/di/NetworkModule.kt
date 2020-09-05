package com.gmail.harsh_chuck.di

import com.gmail.harsh_chuck.helpers.debugProductionManager.DebugProductionManager
import com.gmail.harsh_chuck.network.ApiChuck
import com.gmail.harsh_chuck.network.TextToSpeechApi
import com.gmail.harsh_chuck.network.TypeApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun bindApiChuck(
        gson: GsonConverterFactory,
        rxFactory: RxJava3CallAdapterFactory,
        debugProductionManager: DebugProductionManager
    ): ApiChuck {
        val builder = debugProductionManager.getOkHttpClientBuilder(TypeApi.CHUCK)
        return Retrofit.Builder()
            .baseUrl("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gson)
            .addCallAdapterFactory(rxFactory)
            .client(builder.build())
            .build().create(ApiChuck::class.java)
    }


    @Provides
    fun bindGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
        )
    }

    @Provides
    fun bindRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun bindApiTextToSpeech(
        gson: GsonConverterFactory,
        rxFactory: RxJava3CallAdapterFactory,
        debugProductionManager: DebugProductionManager
    ): TextToSpeechApi {
        val builder = debugProductionManager.getOkHttpClientBuilder(TypeApi.CHUCK)
        return Retrofit.Builder()
            .baseUrl("https://voicerss-text-to-speech.p.rapidapi.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gson)
            .addCallAdapterFactory(rxFactory)
            .client(builder.build())
            .build().create(TextToSpeechApi::class.java)
    }
}

