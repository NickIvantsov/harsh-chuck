package com.gmail.harsh_chuck.helpers.debugProductionManager.productionImpl

import com.gmail.harsh_chuck.helpers.debugProductionManager.TypeVersion
import com.gmail.harsh_chuck.helpers.debugProductionManager.VersionTypes
import com.gmail.harsh_chuck.network.TypeApi
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductionType @Inject constructor() : TypeVersion() {
    protected val builder = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)//todo хардкод
        .connectTimeout(10, TimeUnit.SECONDS)//todo хардкод
        .cache(null)
        .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))

    override fun getVersionTypes(): VersionTypes = VersionTypes.PRODUCTION
    override fun getOkHttpClientBuilder(): OkHttpClient.Builder {

        builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2453399e54msh68c3757e9b925c2p11aaacjsn2b7436b651d6")
                .addHeader("accept", "application/json")
                .build()
            chain.proceed(request)
        })
        return builder
    }

    override fun getOkHttpClientBuilder(type: TypeApi): OkHttpClient.Builder {
        when (type) {
            TypeApi.CHUCK -> {

                builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                        .addHeader(
                            "x-rapidapi-host",
                            "matchilling-chuck-norris-jokes-v1.p.rapidapi.com"
                        )
                        .addHeader(
                            "x-rapidapi-key",
                            "2453399e54msh68c3757e9b925c2p11aaacjsn2b7436b651d6"
                        )
                        .addHeader("accept", "application/json")
                        .build()
                    chain.proceed(request)
                })
                return builder
            }
            TypeApi.TEXT_TO_SPEECH -> {
                builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("x-rapidapi-host", "voicerss-text-to-speech.p.rapidapi.com")
                        .addHeader(
                            "x-rapidapi-key",
                            "2453399e54msh68c3757e9b925c2p11aaacjsn2b7436b651d6"
                        )
                        .build()
                    chain.proceed(request)
                })
                return builder
            }
            else -> {
                throw Exception("неизыестный тип")
            }
        }
    }
}