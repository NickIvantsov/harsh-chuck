package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import io.reactivex.rxjava3.disposables.Disposable

interface IRandomJokes {
    fun resultRequestLiveData(): MutableLiveData<String>
    fun makeRandomJokesRequest(networkService: INetworkService): Disposable
}