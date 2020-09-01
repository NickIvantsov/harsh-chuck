package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class RequestManager @Inject constructor(
    val randomJoke: IRandomJokes,
    val jokesCategories: IJokesCategories
) {

    fun makeRandomJokesRequest(networkService: INetworkService): Disposable =
        randomJoke.makeRandomJokesRequest(networkService)

    fun resultRandomJokeRequestLiveData(): MutableLiveData<String> =
        randomJoke.resultRequestLiveData()


    fun makeJokesCategoriesRequest(networkService: INetworkService): Disposable {
        return jokesCategories.makeJokesCategoriesRequest(networkService)
    }

    fun resultJokesCategoriesLiveData(): MutableLiveData<String> =
        jokesCategories.resultRequestLiveData()
}