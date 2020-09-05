package com.gmail.harsh_chuck.app.fragments.jokeByCategory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.network.request.RequestManager

class JokeByCategoryViewModel @ViewModelInject constructor(val requestManager: RequestManager) :
    ViewModel() {

}