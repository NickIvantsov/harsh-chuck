package com.gmail.harsh_chuck.app.fragments.newRandomJoke

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class NewRandomJokeViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {

    fun makeRandomJokesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return requestManager.makeRandomJokesRequest(networkService, liveData, error)
    }
}

