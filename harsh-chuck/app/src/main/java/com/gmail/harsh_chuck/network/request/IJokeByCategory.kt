package com.gmail.harsh_chuck.network.request

import com.gmail.harsh_chuck.network.INetworkService
import io.reactivex.rxjava3.disposables.Disposable

interface IJokeByCategory:IBaseRequest {
    fun makeRandomJokeByCategoryRequest(
        networkService: INetworkService,
        category: String
    ): Disposable
}