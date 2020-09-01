package com.gmail.harsh_chuck.app.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.harsh_chuck.network.request.RequestManager
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val requestManager: RequestManager) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(requestManager) as T
    }
}