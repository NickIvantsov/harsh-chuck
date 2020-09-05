package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable

interface ICategoriesJokes {
    fun resultRequestLiveData(): MutableLiveData<String>
    fun makeJokesCategoriesRequest(networkService: IChuckRepository): Disposable
}