package com.gmail.harsh_chuck.network.request.requestImpl.randomJokesImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.network.INetworkService
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
        networkService: INetworkService,
        category: String
    ): Disposable {
        return makeRequest(networkService, category)
    }

    private fun makeRequest(
        networkService: INetworkService,
        category: String
    ): Disposable {
        return networkService.getChuckApi().jokesRandom(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                jokeLiveData.value = jokeResponse
            }, error)
    }
}