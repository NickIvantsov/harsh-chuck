package com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.IJokeByCategory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RandomJokesByCategoryRequestImpl @Inject constructor() : IJokeByCategory {

    override fun makeRandomJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return makeRequest(networkService, category, liveData, error)
    }

    private fun makeRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return networkService.jokesRandom(category)
            .subscribeOn(Schedulers.io())
            .map {
                networkService.insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                liveData.value = jokeResponse
            }, error)
    }
}