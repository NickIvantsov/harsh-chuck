package com.gmail.harsh_chuck.app.fragments.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.AppController
import com.gmail.harsh_chuck.helpers.errorTimber
import com.gmail.harsh_chuck.helpers.setText
import com.gmail.harsh_chuck.network.INetworkService
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

    private var selectedCategoriesJokes = ""

    @Inject
    lateinit var networkService: INetworkService


    private val errorLog = errorTimber

    private val viewModel: JokeViewModel by viewModels()

    private val requestJokeByCategory: (String, INetworkService, JokeViewModel) -> Disposable =
        { category: String, networkService: INetworkService, viewModel: JokeViewModel ->
            viewModel.makeJokeByCategoryRequest(
                networkService,
                category
            )
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (context?.applicationContext as AppController).stringData.observe(viewLifecycleOwner) { category ->
            Observable.just(category)
                .subscribe({
                    selectedCategoriesJokes = it
                    makeRequest(
                        selectedCategoriesJokes,
                        networkService,
                        viewModel,
                        requestJokeByCategory
                    )
                }, errorLog)
        }

        jokesByCategoryLiveData(
            viewModel,
            viewLifecycleOwner,
            tv_joke,
            setText,
            errorTimber,
            { textView: TextView,
              setText: (TextView, String) -> Unit,
              error: (Throwable) -> Unit, observable: Observable<JokeRandomResponse> ->
                observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        setText(textView, it.value)
                    }) {
                        error(it)
                    }
            }
        )
    }

    /**
     * метод подписываеться на события LiveDate которые были послены с класса выбора категорий шуток [CategoriesJokesAdapter]
     * @param viewModel вью модель [JokeViewModel]
     * @param lifecycleOwner жизненный цикл [LifecycleOwner]
     * @param tvJoke текстовое поле [TextView]
     * @param setText функция устанвовки текстового поля
     * @param error функция обработки ошибки
     * @param push функция обьединяющая все выше перечисленное
     *
     */
    private fun jokesByCategoryLiveData(
        viewModel: JokeViewModel,
        lifecycleOwner: LifecycleOwner,
        tvJoke: TextView,
        setText: (TextView, String) -> Unit,
        error: (Throwable) -> Unit,
        push: (
            TextView,
            setText: (TextView, String) -> Unit,
            error: (Throwable) -> Unit,
            Observable<JokeRandomResponse>
        ) -> Disposable
    ) {
        viewModel.jokesByCategoryLiveData.observe(lifecycleOwner) { joke ->
            push(tvJoke, setText, error, Observable.just(joke))
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newJokePressed()
        btn_category.clicks()
            .subscribe({
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
                findNavController().popBackStack()
            }, errorLog)
    }

    private fun newJokePressed() {
        btn_new_joke.clicks()
            .subscribe({
                makeRequest(
                    selectedCategoriesJokes,
                    networkService,
                    viewModel,
                    requestJokeByCategory
                )
            }, errorLog)
    }

    private fun makeRequest(
        selectedCategoriesJokes: String,
        networkService: INetworkService,
        viewModel: JokeViewModel,
        request: (String, INetworkService, JokeViewModel) -> Disposable
    ): Disposable {
        return request(selectedCategoriesJokes, networkService, viewModel)
    }
}

