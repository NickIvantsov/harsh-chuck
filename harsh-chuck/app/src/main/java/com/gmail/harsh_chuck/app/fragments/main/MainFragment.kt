package com.gmail.harsh_chuck.app.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.Screens
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
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
    lateinit var chuckRepository: IChuckRepository

    @Inject
    lateinit var navigator: AppNavigator

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var jokeLiveData: MutableLiveData<JokeRandomResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newJokeRequest(viewModel, chuckRepository, jokeLiveData, errorTimber)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        jokeLiveData()
    }

    private fun jokeLiveData() {
        jokeLiveData.observe(viewLifecycleOwner) { jokeText ->
            Observable.just(jokeText)
                .subscribe({
                    setTvJokeText(it.value)
                }) {
                    errorLog(it)
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newRandomPressed()
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
                newJokeRequest(viewModel, chuckRepository, jokeLiveData, errorTimber)
            }, errorTimber)
    }

    val newJokeRequest = { viewModel: MainViewModel, networkService: IChuckRepository,
                           liveData: MutableLiveData<JokeRandomResponse>,
                           error: (Throwable) -> Unit ->
        viewModel.makeRandomJokesRequest(networkService, liveData, error)
    }

    private fun setTvJokeText(textValue: String) {
        tv_joke.text = textValue
    }

    private fun errorLog(it: Throwable?) {
        viewModel.errorLog(it)
    }

}