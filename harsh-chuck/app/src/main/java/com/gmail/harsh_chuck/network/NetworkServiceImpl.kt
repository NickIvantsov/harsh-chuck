package com.gmail.harsh_chuck.network

import com.gmail.harsh_chuck.helpers.debugProductionManager.DebugProductionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

/**
 * класс предоставляющий единую точку входа для доступа к запросам.Список запросов перечислен в  [ApiChuck]
 */
class NetworkService @Inject constructor(interceptor: HttpLoggingInterceptor) : INetworkService {
    companion object {
        private const val BASE_TEXT_TO_SPEECH_URL =
            "https://voicerss-text-to-speech.p.rapidapi.com"
    }


    private val builderTextToSpeech: OkHttpClient.Builder =
        DebugProductionManager(interceptor).getOkHttpClientBuilder(TypeApi.TEXT_TO_SPEECH)

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private val clientTextToSpeech: OkHttpClient = builderTextToSpeech.build()


    private val mRetrofitTextToSpeech = Retrofit.Builder()
        .baseUrl(BASE_TEXT_TO_SPEECH_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(clientTextToSpeech)
        .build()

    override fun getTextToSpeechApi(): TextToSpeechApi =
        mRetrofitTextToSpeech.create(TextToSpeechApi::class.java)

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

enum class TypeApi {
    CHUCK,
    TEXT_TO_SPEECH
}