package com.gmail.harsh_chuck.network.request

import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable

interface IRandomJokes : IBaseRequest {
    fun makeRandomJokesRequest(networkService: IChuckRepository): Disposable
}