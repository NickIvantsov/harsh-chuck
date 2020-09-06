package com.gmail.harsh_chuck.app.fragments.joke

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
import com.gmail.harsh_chuck.domain.AppController
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.helpers.setText
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.joke_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class JokeFragment : Fragment() {

    companion object {
        fun newInstance() = JokeFragment()
    }

    val stringDataLiveDataObservable = { liveData: MutableLiveData<String>,
                                         lifecycleOwner: LifecycleOwner,
                                         viewModel: JokeViewModel,
                                         chuckRepository: IChuckRepository,
                                         jokeResponseLiveData: MutableLiveData<JokeRandomResponse>,
                                         errorRequest: (Throwable) -> Unit,
                                         errorClick: (Throwable) -> Unit,
                                         requestJokeByCategory: (
                                             IChuckRepository,
                                             String,
                                             MutableLiveData<JokeRandomResponse>,
                                             (Throwable) -> Unit,
                                             JokeViewModel
                                         ) -> Disposable
        ->
        liveData.observe(lifecycleOwner) { category ->
            Observable.just(category)
                .subscribe({
                    viewModel.makeRequest(
                        it,
                        chuckRepository,
                        viewModel,
                        jokeResponseLiveData,
                        errorRequest,
                        requestJokeByCategory
                    )
                }, errorClick)
        }
    }

    @Inject
    lateinit var chuckRepository: IChuckRepository


    private val errorLog = errorTimber

    private val viewModel: JokeViewModel by viewModels()

    @Inject
    lateinit var jokeResponseLiveData: MutableLiveData<JokeRandomResponse>

    private val requestJokeByCategory: (
        IChuckRepository,
        String,
        MutableLiveData<JokeRandomResponse>,
        (Throwable) -> Unit,
        JokeViewModel
    ) -> Disposable =
        { networkService: IChuckRepository,
          category: String,
          liveData: MutableLiveData<JokeRandomResponse>,
          error: (Throwable) -> Unit,
          viewModel: JokeViewModel ->
            viewModel.makeJokeByCategoryRequest(
                networkService,
                category,
                liveData, error
            )
        }

    val btnCategoryPressed =
        { btn: Button,
          navController: NavController,
          liveData: MutableLiveData<Boolean>,
          popBackStack: (NavController) -> Boolean,
          errorClick: (Throwable) -> Unit ->

            btn.clicks()
                .subscribe({
                    liveData.value = true
                    popBackStack(navController)
                }, errorClick)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        tv_joke.movementMethod = ScrollingMovementMethod()

        stringDataLiveDataObservable(
            (context?.applicationContext as AppController).stringData,
            viewLifecycleOwner,
            viewModel,
            chuckRepository,
            jokeResponseLiveData,
            errorLog,
            errorLog,
            requestJokeByCategory
        )

        jokesByCategoryLiveData(
            jokeResponseLiveData,
            viewLifecycleOwner,
            tv_joke,
            tv_category,
            setText,
            errorTimber
        ) { tvJoke: TextView,
            tvCategory: TextView,
            setText: (TextView, String) -> Unit,
            error: (Throwable) -> Unit,
            observable: Observable<JokeRandomResponse> ->
            observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(tvJoke, it.value)
                    setText(tvCategory, it.categories.first())
                }) {
                    error(it)
                }
        }
    }

    val jokesByCategoryLiveData = { jokeResponseLiveData: MutableLiveData<JokeRandomResponse>,
                                    lifecycleOwner: LifecycleOwner,
                                    tvJoke: TextView,
                                    tvCategory: TextView,
                                    setText: (TextView, String) -> Unit,
                                    error: (Throwable) -> Unit,
                                    push: (
                                        TextView,
                                        TextView,
                                        setText: (TextView, String) -> Unit,
                                        error: (Throwable) -> Unit,
                                        Observable<JokeRandomResponse>
                                    ) -> Disposable ->
        jokeResponseLiveData.observe(lifecycleOwner) { joke ->
            push(tvJoke, tvCategory, setText, error, Observable.just(joke))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.newJokePressed(
            btn_new_joke,
            chuckRepository,
            viewModel,
            jokeResponseLiveData,
            ((context?.applicationContext) as AppController).stringData,
            viewLifecycleOwner,
            errorLog,
            requestJokeByCategory,
            viewModel.makeRequest
        )

        btnCategoryPressed(
            btn_category,
            findNavController(),
            ((context?.applicationContext) as AppController).jokeCategoryLiveData,
            popBackStack,
            errorLog
        )
    }
}

