package com.gmail.harsh_chuck.domain.repository

import androidx.lifecycle.LiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import io.reactivex.rxjava3.core.Single

interface IChuckRepository {
    fun jokesRandom(): Single<JokeRandomResponse?>
    fun jokesRandom(category: String): Single<JokeRandomResponse?>
    fun jokesCategories(): Single<String?>
    fun getAllJokes(): LiveData<List<JokeRandomResponse?>?>
    fun insert(joke: JokeRandomResponse?)
    fun deleteAll()
}