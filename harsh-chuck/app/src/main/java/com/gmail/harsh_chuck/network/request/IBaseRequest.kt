package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData

interface IBaseRequest {
    fun resultRequestLiveData(): MutableLiveData<String>
}