package com.gmail.harsh_chuck.app.fragments.newRandomJoke

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.popBackStack
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.helpers.setText
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.new_random_joke_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class NewRandomJokeFragment : Fragment() {

    companion object {
        fun newInstance() = NewRandomJokeFragment()
    }

    @Inject
    lateinit var chuckRepository: IChuckRepository

    @Inject
    lateinit var jokeLiveData: MutableLiveData<JokeRandomResponse>

    private val newRandomPressed = { btn: Button,
                                     errorClick: (Throwable) -> Unit,
                                     errorRequest: (Throwable) -> Unit,
                                     viewModel: NewRandomJokeViewModel,
                                     chuckRepository: IChuckRepository,
                                     jokeLiveData: MutableLiveData<JokeRandomResponse>,
                                     request: (
                                         NewRandomJokeViewModel, IChuckRepository,
                                         MutableLiveData<JokeRandomResponse>,
                                         logError: (Throwable) -> Unit
                                     ) -> Unit ->

        btn.clicks()
            .subscribe({
                request(viewModel, chuckRepository, jokeLiveData, errorRequest)
            }, errorClick)
    }

    private val backPressed = { btn: Button,
                                navController: NavController,
                                errorClick: (Throwable) -> Unit,
                                popBackStack: (NavController) -> Boolean ->
        btn.clicks()
            .subscribe({
                popBackStack(navController)
            }, errorClick)
    }

    private val viewModel: NewRandomJokeViewModel by viewModels()

    private val newJokeRequest = { viewModel: NewRandomJokeViewModel,
                                   networkService: IChuckRepository,
                                   liveData: MutableLiveData<JokeRandomResponse>,
                                   error: (Throwable) -> Unit ->
        viewModel.makeRandomJokesRequest(networkService, liveData, error)
    }
    private val jokeLiveDataFunc = { tvJoke: TextView,
                                     jokeLiveData: MutableLiveData<JokeRandomResponse>,
                                     viewLifecycleOwner: LifecycleOwner,
                                     error: (Throwable) -> Unit,
                                     setText: (TextView, String) -> Unit ->
        Unit
        jokeLiveData.observe(viewLifecycleOwner) { jokeText ->
            Observable.just(jokeText)
                .subscribe({
                    setText(tvJoke, it.value)
                }, error)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_random_joke_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newJokeRequest(viewModel, chuckRepository, jokeLiveData, errorTimber)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_joke.movementMethod = ScrollingMovementMethod()
        jokeLiveDataFunc(tv_joke, jokeLiveData, viewLifecycleOwner, errorTimber, setText)

        backPressed(
            btn_back,
            findNavController(),
            errorTimber,
            popBackStack
        )
        newRandomPressed(
            btn_new_joke,
            errorTimber,
            errorTimber,
            viewModel,
            chuckRepository,
            jokeLiveData,
            newJokeRequest
        )
    }
}