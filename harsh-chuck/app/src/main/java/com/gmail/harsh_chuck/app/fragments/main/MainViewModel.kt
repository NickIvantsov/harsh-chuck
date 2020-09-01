package com.gmail.harsh_chuck.app.fragments.main

import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.network.NetworkService
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(val requestManager: RequestManager) : ViewModel() {

    val jokeLiveData = requestManager.resultRandomJokeRequestLiveData()

    fun makeRandomJokesRequest(networkService: NetworkService): Disposable {
        return requestManager.makeRandomJokesRequest(networkService)
    }
}