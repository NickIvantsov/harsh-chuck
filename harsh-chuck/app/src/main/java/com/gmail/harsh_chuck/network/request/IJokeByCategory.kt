package com.gmail.harsh_chuck.network.request

import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable

interface IJokeByCategory : IBaseRequest {
    fun makeRandomJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String
    ): Disposable
}