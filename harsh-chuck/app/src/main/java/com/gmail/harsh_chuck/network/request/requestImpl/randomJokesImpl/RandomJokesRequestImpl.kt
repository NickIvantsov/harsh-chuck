package com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.network.request.IRandomJokes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RandomJokesRequestImpl @Inject constructor() : IRandomJokes {
    private val jokeLiveData = MutableLiveData<JokeRandomResponse>()

    private val error = errorTimber
    override fun resultRequestLiveData() = jokeLiveData

    override fun makeRandomJokesRequest(networkService: IChuckRepository): Disposable {
        return makeRequest(networkService)
    }

    private fun makeRequest(networkService: IChuckRepository): Disposable {
        return networkService.jokesRandom()
            .subscribeOn(Schedulers.io())
            .map {
                networkService.insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                networkService.insert(jokeResponse)
                jokeLiveData.value = jokeResponse
            }, error)
    }
}