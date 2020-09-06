package com.gmail.harsh_chuck.app.fragments.joke

import android.widget.Button
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import com.jakewharton.rxbinding.view.clicks
import io.reactivex.rxjava3.disposables.Disposable

class JokeViewModel @ViewModelInject constructor(val requestManager: RequestManager) : ViewModel() {

    fun makeJokeByCategoryRequest(
        networkService: IChuckRepository,
        category: String,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return requestManager.makeJokeByCategoryRequest(networkService, category, liveData, error)
    }

    val newJokePressed = { btn: Button,
                           chuckRepository: IChuckRepository,
                           viewModel: JokeViewModel,
                           jokeRandomResponseliveData: MutableLiveData<JokeRandomResponse>,
                           categoryliveData: MutableLiveData<String>,
                           lifecycleOwner: LifecycleOwner,
                           error: (Throwable) -> Unit,
                           request: (
                               IChuckRepository,
                               String,
                               MutableLiveData<JokeRandomResponse>,
                               (Throwable) -> Unit,
                               JokeViewModel
                           ) -> Disposable,
                           makeRequest: (
                               String,
                               IChuckRepository,
                               JokeViewModel,
                               MutableLiveData<JokeRandomResponse>,
                               (Throwable) -> Unit,
                               request: (
                                   IChuckRepository,
                                   String,
                                   MutableLiveData<JokeRandomResponse>,
                                   (Throwable) -> Unit,
                                   JokeViewModel
                               ) -> Disposable
                           ) -> Disposable ->

        btn.clicks()
            .subscribe({
                categoryliveData.observe(lifecycleOwner){
                    makeRequest(
                        it,
                        chuckRepository,
                        viewModel,
                        jokeRandomResponseliveData,
                        error,
                        request
                    )
                }

            }, error)
    }
    val makeRequest = { selectedCategoriesJokes: String,
                                networkService: IChuckRepository,
                                viewModel: JokeViewModel,
                                liveData: MutableLiveData<JokeRandomResponse>,
                                error: (Throwable) -> Unit,
                                request: (
                                    IChuckRepository,
                                    String,
                                    MutableLiveData<JokeRandomResponse>,
                                    (Throwable) -> Unit,
                                    JokeViewModel
                                ) -> Disposable ->
        request(networkService, selectedCategoriesJokes, liveData, error, viewModel)
    }

}