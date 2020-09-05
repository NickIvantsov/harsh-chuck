package com.gmail.harsh_chuck.domain.repository.chuckImpl

import androidx.lifecycle.LiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.data.room.JokeDao
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.ApiChuck
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChuckRepository @Inject constructor(val jokeDao: JokeDao, val apiChuck: ApiChuck) :
    IChuckRepository {

    override fun jokesRandom(): Single<JokeRandomResponse?> {
        return apiChuck.jokesRandom()
    }

    override fun jokesRandom(category: String): Single<JokeRandomResponse?> {
        return apiChuck.jokesRandom(category)
    }

    override fun jokesCategories(): Single<String?> {
        return apiChuck.jokesCategories()
    }

    override fun getAllJokes(): LiveData<List<JokeRandomResponse?>?> {
        return jokeDao.getAllJokes()
    }

    override fun insert(joke: JokeRandomResponse?) {
        jokeDao.insert(joke)
    }

    override fun deleteAll() {
        jokeDao.deleteAll()
    }
}