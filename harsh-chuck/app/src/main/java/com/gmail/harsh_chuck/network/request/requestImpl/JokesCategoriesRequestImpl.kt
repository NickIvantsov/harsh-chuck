package com.gmail.harsh_chuck.network.request.requestImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.network.INetworkService
import com.gmail.harsh_chuck.network.request.IJokesCategories
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class JokesCategoriesRequestImpl @Inject constructor() : IJokesCategories {
    val jokesCategoriesLiveData = MutableLiveData<String>()
    override fun makeJokesCategoriesRequest(networkService: INetworkService): Disposable {
        return networkService.getChuckApi().jokesCategories()
            .subscribeOn(Schedulers.io())
            .map {
                it?.replace("[", "")

            }.map {
                it?.replace("]", "")
            }
            .map {
                it?.replace("\"", "")
            }
            .map {
                it?.split(",") ?: ArrayList()
            }
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseItem ->
                jokesCategoriesLiveData.value = responseItem
            }) {
                Timber.e(it)
            }
    }

    override fun resultRequestLiveData(): MutableLiveData<String> = jokesCategoriesLiveData
}