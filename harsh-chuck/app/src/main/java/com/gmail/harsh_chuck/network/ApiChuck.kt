package com.gmail.harsh_chuck.network

import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiChuck {
    @GET("jokes/random")
    fun jokesRandom(): Single<JokeRandomResponse?>

    @GET("jokes/random")
    fun jokesRandom(@Query("category") category: String): Single<JokeRandomResponse?>

    @GET("/jokes/categories")
    fun jokesCategories(): Single<String?>
}