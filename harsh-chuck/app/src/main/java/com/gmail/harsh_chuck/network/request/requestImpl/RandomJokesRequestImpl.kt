package com.gmail.harsh_chuck.network.request.requestImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import com.gmail.harsh_chuck.network.request.IRandomJokes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RandomJokesRequestImpl @Inject constructor() : IRandomJokes {
    private val jokeLiveData = MutableLiveData<String>()

    override fun resultRequestLiveData() = jokeLiveData

    override fun makeRandomJokesRequest(networkService: INetworkService): Disposable {
        return makeRequest(networkService)
    }

    private fun makeRequest(networkService: INetworkService): Disposable {
        return networkService.getChuckApi().jokesRandom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokeResponse ->
                Timber.d(jokeResponse.toString())
                jokeResponse?.value?.let { textValue ->
                    jokeLiveData.value = textValue
                }
            }) {
                Timber.e(it)
            }
    }
}