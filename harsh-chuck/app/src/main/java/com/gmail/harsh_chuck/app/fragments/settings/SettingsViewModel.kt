package com.gmail.harsh_chuck.app.fragments.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class SettingsViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {

    val jokesCategoriesLiveData = requestManager.resultJokesCategoriesLiveData()
    fun makeJokesCategoriesRequest(networkService: IChuckRepository): Disposable {
        return requestManager.makeJokesCategoriesRequest(networkService)
    }
}