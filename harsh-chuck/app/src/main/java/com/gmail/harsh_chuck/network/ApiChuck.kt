package com.gmail.harsh_chuck.network

import com.gmail.harsh_chuck.data.chuckApi.JokeRandomResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface ApiChuck {
    @GET("jokes/random")
    fun jokesRandom(): Single<JokeRandomResponse?>

    @GET("/jokes/categories")
    fun jokesCategories(): Single<String?>
}