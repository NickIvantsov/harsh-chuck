package com.gmail.harsh_chuck.app.fragments.main

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.activities.FileUtil
import com.gmail.harsh_chuck.network.NetworkService
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    val networkService = NetworkService
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        viewModel.jokeLiveData.observe(viewLifecycleOwner) { jokeText ->
            Observable.just(jokeText)
                .subscribe({
                    setTvJokeText(it)
                }) {
                    errorLog(it)
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newRandomPressed()
        settingsPressed()
    }

    private fun settingsPressed() {
        btn_settings.clicks()
            .subscribe({
                findNavController().navigate(R.id.settingsFragment)
            }) {
                errorLog(it)
            }
    }

    private fun newRandomPressed() {
        new_random.clicks()
            .subscribe({
                viewModel.makeRandomJokesRequest(networkService)
            }) {
                errorLog(it)
            }
    }

    private fun errorLog(it: Throwable?) {
        Timber.e(it)
    }


    private fun setTvJokeText(textValue: String) {
        tv_joke.text = textValue
    }


    private fun makeDir(): Boolean {
        //getExternalStorageDirectory устарел начиная с 29 API
        return FileUtil.createDir(Environment.getExternalStorageDirectory().path + "/mp3Files")//todo хардкод
    }

    private fun convertBytesToFile(bytearray: ByteArray) {
        val fos =
            FileOutputStream(Environment.getExternalStorageDirectory().path + "/hello-2.mp3", false)
        fos.write(bytearray)
        fos.close()
    }
}