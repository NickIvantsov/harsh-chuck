package com.gmail.harsh_chuck.app.activities

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gmail.harsh_chuck.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isStoragePermissionGranted()
//        makeRequest(networkService)

    }

    private fun errorLog(it: Throwable?) {
        Timber.e(it)
    }

    var mp: MediaPlayer = MediaPlayer()

    //region использовать позже
    //    private fun makeRequest(networkService: NetworkService) {
//        networkService.getChuckApi().jokesRandom()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { jokeResponse ->
//                Timber.d(jokeResponse.toString())
//                jokeResponse?.value?.let { textValue ->
//                    tv_joke.text = textValue
//                    networkService.getTextToSpeechApi().convertTextToSpeech(src = textValue)
//                        .subscribeOn(Schedulers.io())
//                }
//            }
//            .map { response ->
//                Timber.d(response)
//
//                Timber.d("makeDir() = %s", makeDir())
//                val replaceString: String =
//                    response.replace("data:audio/mpeg;base64,", "")
//
//
//                val data: ByteArray = Base64.decode(replaceString, Base64.DEFAULT)
//                convertBytesToFile(data)
//
//            }
//            .subscribe({
//                if (mp != null) {
//                    mp.stop()
//                    mp.reset()
//                }
//
//                mp.setDataSource(Environment.getExternalStorageDirectory().path + "/hello-2.mp3")
//                mp.prepareAsync()
//
//                mp.setOnPreparedListener { mediaPlayer ->
//                    mediaPlayer.start()
//                }
//            }) {
//                Timber.e(it)
//            }
//    }
    //endregion


    private fun requestPerm() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            123
        )
    }

    private fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                requestPerm()
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }

}


object FileUtil {
    fun createDir(pathname: String): Boolean {
        return try {
            val file = File(pathname)
            file.mkdir()
        } catch (ex: Exception) {
            Timber.e(ex)
            false
        }
    }

}