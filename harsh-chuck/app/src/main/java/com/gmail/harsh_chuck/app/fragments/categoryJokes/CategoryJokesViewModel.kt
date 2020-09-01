package com.gmail.harsh_chuck.app.fragments.categoryJokes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.network.INetworkService
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class CategoryJokesViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {
    val jokesCategoriesLiveData = requestManager.resultJokesCategoriesLiveData()
    fun makeJokesCategoriesRequest(networkService: INetworkService): Disposable {
        return requestManager.makeJokesCategoriesRequest(networkService)
    }
}