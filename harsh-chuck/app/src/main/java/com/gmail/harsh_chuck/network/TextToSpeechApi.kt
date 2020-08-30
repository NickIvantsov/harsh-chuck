package com.gmail.harsh_chuck.network

import com.gmail.harsh_chuck.data.textToSpeech.TextToSpeechRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface TextToSpeechApi {
    /**
     * @param r The speech rate (speed). Allows values: from -10 (slowest speed) up to 10 (fastest speed). Default value: 0 (normal speed)
     * @param c The speech audio codec (mp3)
     * @param f The speech audio formats
     * @param src The textual content for converting to speech
     * @param hl The textual content language
     * @param key The API key
     */
    @GET("/")
    fun convertTextToSpeech(
        @Query("r") r: Int = 0,
        @Query("c") c: String = "mp3",
        @Query("f") f: String = "8khz_8bit_mono",
        @Query("src") src: String = "Hello, world!",
        @Query("hl") hl: String = "en-us",
        @Query("b64") b64: Boolean = true,
        @Query("key") key: String = "bda7e7dc69864f698ef1e3b571843b3a"
    ): Single<String>

    @POST("/")
    fun convertTextToSpeech(
        @Body request: TextToSpeechRequest,
        @Query("key") key: String = "bda7e7dc69864f698ef1e3b571843b3a"
    ): Single<String>
}