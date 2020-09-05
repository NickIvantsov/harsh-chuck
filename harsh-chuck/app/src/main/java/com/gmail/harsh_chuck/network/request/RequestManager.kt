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

    fun makeRandomJokesRequest(networkService: IChuckRepository): Disposable =
        randomJoke.makeRandomJokesRequest(networkService)

    fun resultRandomJokeRequestLiveData(): MutableLiveData<JokeRandomResponse> =
        randomJoke.resultRequestLiveData()


    fun makeJokesCategoriesRequest(networkService: IChuckRepository): Disposable {
        return categoriesJokes.makeJokesCategoriesRequest(networkService)
    }

    fun resultJokesCategoriesLiveData(): MutableLiveData<String> =
        categoriesJokes.resultRequestLiveData()

    fun makeJokeByCategoryRequest(networkService: IChuckRepository, category: String): Disposable {
        return jokeByCategory.makeRandomJokeByCategoryRequest(networkService, category)
    }

    fun resultJokeByCategoryLiveData(): MutableLiveData<JokeRandomResponse> =
        jokeByCategory.resultRequestLiveData()
}