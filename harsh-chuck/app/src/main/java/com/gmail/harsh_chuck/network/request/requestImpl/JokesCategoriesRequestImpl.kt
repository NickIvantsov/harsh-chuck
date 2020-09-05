package com.gmail.harsh_chuck.network.request.requestImpl

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.ICategoriesJokes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class JokesCategoriesRequestImpl @Inject constructor() : ICategoriesJokes {

    override fun makeJokesCategoriesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<String>,
        error: (Throwable) -> Unit
    ): Disposable {
        return networkService.jokesCategories()
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
                liveData.value = responseItem
            }, error)
    }

}