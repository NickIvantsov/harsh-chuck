package com.gmail.harsh_chuck.app.fragments.joke

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class JokeViewModel @ViewModelInject constructor(val requestManager: RequestManager) : ViewModel() {
    val jokesByCategoryLiveData = requestManager.resultJokeByCategoryLiveData()
    fun makeJokeByCategoryRequest(networkService: IChuckRepository, category: String): Disposable {
        return requestManager.makeJokeByCategoryRequest(networkService, category)
    }
}