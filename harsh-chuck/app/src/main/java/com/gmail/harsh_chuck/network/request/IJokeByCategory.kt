package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable

interface IJokeByCategory {
    fun makeRandomJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable
}