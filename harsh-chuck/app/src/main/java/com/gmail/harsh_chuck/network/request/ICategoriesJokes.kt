package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import io.reactivex.rxjava3.disposables.Disposable

interface ICategoriesJokes {
    fun resultRequestLiveData(): MutableLiveData<String>
    fun makeJokesCategoriesRequest(networkService: INetworkService): Disposable
}