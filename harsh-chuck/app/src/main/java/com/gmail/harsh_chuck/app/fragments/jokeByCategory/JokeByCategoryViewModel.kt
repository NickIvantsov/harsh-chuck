package com.gmail.harsh_chuck.app.fragments.jokeByCategory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.network.INetworkService
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable

class JokeByCategoryViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {

}