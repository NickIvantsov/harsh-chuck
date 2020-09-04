package com.gmail.harsh_chuck.network.request

import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse

interface IBaseRequest {
    fun resultRequestLiveData(): MutableLiveData<JokeRandomResponse>
}