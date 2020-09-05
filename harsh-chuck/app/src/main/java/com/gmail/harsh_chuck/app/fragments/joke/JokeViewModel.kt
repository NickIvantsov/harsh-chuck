package com.gmail.harsh_chuck.app.fragments.joke

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class JokeViewModel @ViewModelInject constructor(val requestManager: RequestManager) : ViewModel() {

    fun makeJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return requestManager.makeJokeByCategoryRequest(networkService, category, liveData, error)
    }
}