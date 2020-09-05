package com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.IRandomJokes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RandomJokesRequestImpl @Inject constructor() : IRandomJokes {
    override fun makeRandomJokesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return makeRequest(networkService, liveData, error)
    }

    private fun makeRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return networkService.jokesRandom()
            .subscribeOn(Schedulers.io())
            .map {
                networkService.insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                networkService.insert(jokeResponse)
                liveData.value = jokeResponse
            }, error)
    }
}