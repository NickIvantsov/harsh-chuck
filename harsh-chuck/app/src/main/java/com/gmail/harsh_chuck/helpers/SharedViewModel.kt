package com.gmail.harsh_chuck.helpers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel @ViewModelInject constructor() : ViewModel() {

    val stringData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}