package com.gmail.harsh_chuck.app.fragments.categoryJokes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class CategoryJokesViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {
    fun makeJokesCategoriesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<String>,
        error: (Throwable) -> Unit
    ): Disposable {
        return requestManager.makeJokesCategoriesRequest(networkService, liveData, error)
    }
}