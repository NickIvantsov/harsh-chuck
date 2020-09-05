package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable

interface ICategoriesJokes {

    fun makeJokesCategoriesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<String>,
        error: (Throwable) -> Unit
    ): Disposable
}