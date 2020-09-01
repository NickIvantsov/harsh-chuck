package com.gmail.harsh_chuck.app.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.Screens
import com.gmail.harsh_chuck.network.INetworkService
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var networkService: INetworkService

    @Inject
    lateinit var navigator: AppNavigator

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        jokeLiveData()
    }

    private fun jokeLiveData() {
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
        jokeByCategoryPressed()
    }

    private fun jokeByCategoryPressed() {
        btn_joke_by_category.clicks()
            .subscribe({
                navigator.navigateTo(Screens.JOKE_BY_CATEGORY)
            }) {
                errorLog(it)
            }
    }

    private fun settingsPressed() {
        btn_settings.clicks()
            .subscribe({
                navigator.navigateTo(Screens.SETTINGS)
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

    private fun setTvJokeText(textValue: String) {
        tv_joke.text = textValue
    }

    private fun errorLog(it: Throwable?) {
        viewModel.errorLog(it)
    }
}