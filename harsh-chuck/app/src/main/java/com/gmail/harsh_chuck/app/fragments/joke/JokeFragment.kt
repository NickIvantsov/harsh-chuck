package com.gmail.harsh_chuck.app.fragments.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.adapters.RadioAdapter.Companion.selectedCategoriesJokes
import com.gmail.harsh_chuck.domain.AppController
import com.gmail.harsh_chuck.network.INetworkService
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.joke_fragment.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class JokeFragment : Fragment() {

    companion object {
        fun newInstance() = JokeFragment()
    }

    @Inject
    lateinit var networkService: INetworkService

    private val viewModel: JokeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeRequest()
        jokesByCategoryLiveData()
    }

    private fun jokesByCategoryLiveData() {
        viewModel.jokesByCategoryLiveData.observe(viewLifecycleOwner) { joke ->
            Observable.just(joke)
                .subscribe({
                    tv_joke.text = it
                }) {
                    errorLog(it)
                }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newJokePressed()
        btn_category.clicks()
            .subscribe({
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
                findNavController().popBackStack()
            }) {
                errorLog(it)
            }
    }

    private fun newJokePressed() {
        btn_new_joke.clicks()
            .subscribe({
                makeRequest()
            }) {
                errorLog(it)
            }
    }

    private fun makeRequest() {
        viewModel.makeJokeByCategoryRequest(networkService, selectedCategoriesJokes)
    }

    private fun errorLog(throwable: Throwable) {
        Timber.e(throwable)
    }
}