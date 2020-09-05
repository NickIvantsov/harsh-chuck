package com.gmail.harsh_chuck.app.fragments.main

import android.os.Environment
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.harsh_chuck.app.activities.FileUtil
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.network.request.RequestManager
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.io.FileOutputStream

class MainViewModel @ViewModelInject constructor(val requestManager: RequestManager) : ViewModel() {

    fun makeRandomJokesRequest(
        networkService: IChuckRepository,
        liveData: MutableLiveData<JokeRandomResponse>,
        error: (Throwable) -> Unit
    ): Disposable {
        return requestManager.makeRandomJokesRequest(networkService, liveData, error)
    }

    fun makeDir(): Boolean {
        //getExternalStorageDirectory устарел начиная с 29 API
        return FileUtil.createDir(Environment.getExternalStorageDirectory().path + "/mp3Files")//todo хардкод
    }

    fun convertBytesToFile(bytearray: ByteArray) {
        val fos =
            FileOutputStream(
                Environment.getExternalStorageDirectory().path + "/hello-2.mp3",
                false
            )//todo хардкод
        fos.write(bytearray)
        fos.close()
    }

    fun errorLog(it: Throwable?) {
        Timber.e(it)
    }
}