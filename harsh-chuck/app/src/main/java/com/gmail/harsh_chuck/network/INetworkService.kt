package com.gmail.harsh_chuck.network

interface INetworkService {
    fun getChuckApi(): ApiChuck
    fun getTextToSpeechApi(): TextToSpeechApi
}