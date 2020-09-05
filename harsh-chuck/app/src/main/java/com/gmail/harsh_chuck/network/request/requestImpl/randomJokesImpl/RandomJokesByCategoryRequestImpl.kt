package com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.network.request.IJokeByCategory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RandomJokesByCategoryRequestImpl @Inject constructor() : IJokeByCategory {
    private val jokeLiveData = MutableLiveData<JokeRandomResponse>()

    private val error = errorTimber
    override fun resultRequestLiveData(): MutableLiveData<JokeRandomResponse> = jokeLiveData


    override fun makeRandomJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String
    ): Disposable {
        return makeRequest(networkService, category)
    }

    private fun makeRequest(
        networkService: IChuckRepository,
        category: String
    ): Disposable {
        return networkService.jokesRandom(category)
            .subscribeOn(Schedulers.io())
            .map {
                networkService.insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                jokeLiveData.value = jokeResponse
            }, error)
    }
}