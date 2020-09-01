package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class RequestManager @Inject constructor(val randomJoke: IRandomJokes) {

    fun makeRandomJokesRequest(networkService: INetworkService): Disposable =
        randomJoke.makeRandomJokesRequest(networkService)

    fun resultRandomJokeRequestLiveData(): MutableLiveData<String> =
        randomJoke.resultRequestLiveData()
}