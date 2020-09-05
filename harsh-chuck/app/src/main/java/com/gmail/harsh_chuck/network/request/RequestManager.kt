package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class RequestManager @Inject constructor(
    val randomJoke: IRandomJokes,
    val categoriesJokes: ICategoriesJokes,
    val jokeByCategory: IJokeByCategory
) {

    fun makeRandomJokesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable =
        randomJoke.makeRandomJokesRequest(networkService, liveData, error)

    fun makeJokesCategoriesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<String>,
        error: (Throwable) -> Unit
    ): Disposable {
        return categoriesJokes.makeJokesCategoriesRequest(networkService, liveData, error)
    }

    fun makeJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return jokeByCategory.makeRandomJokeByCategoryRequest(
            networkService,
            category,
            liveData,
            error
        )
    }
}