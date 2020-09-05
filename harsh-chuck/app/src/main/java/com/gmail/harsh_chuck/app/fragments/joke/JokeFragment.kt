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

    private var selectedCategoriesJokes = ""

    @Inject
    lateinit var chuckRepository: IChuckRepository


    private val errorLog = errorTimber

    private val viewModel: JokeViewModel by viewModels()

    private val requestJokeByCategory: (String, IChuckRepository, JokeViewModel) -> Disposable =
        { category: String, networkService: IChuckRepository, viewModel: JokeViewModel ->
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
                        chuckRepository,
                        viewModel,
                        requestJokeByCategory
                    )
                }, errorLog)
        }

        jokesByCategoryLiveData(
            viewModel,
            viewLifecycleOwner,
            tv_joke,
            tv_category,
            setText,
            errorTimber,
            { tvJoke: TextView,
              tvCategory: TextView,
              setText: (TextView, String) -> Unit,
              error: (Throwable) -> Unit,
              observable: Observable<JokeRandomResponse> ->
                observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        setText(tvJoke, it.value)
                        setText(tvCategory, it.categories.first() as String)
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
        tvCategory: TextView,
        setText: (TextView, String) -> Unit,
        error: (Throwable) -> Unit,
        push: (
            TextView,
            TextView,
            setText: (TextView, String) -> Unit,
            error: (Throwable) -> Unit,
            Observable<JokeRandomResponse>
        ) -> Disposable
    ) {
        viewModel.jokesByCategoryLiveData.observe(lifecycleOwner) { joke ->
            push(tvJoke, tvCategory, setText, error, Observable.just(joke))
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newJokePressed()
        btn_category.clicks()
            .subscribe({
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = true
                findNavController().popBackStack()
            }, errorLog)
    }

    private fun newJokePressed() {
        btn_new_joke.clicks()
            .subscribe({
                makeRequest(
                    selectedCategoriesJokes,
                    chuckRepository,
                    viewModel,
                    requestJokeByCategory
                )
            }, errorLog)
    }

    private fun makeRequest(
        selectedCategoriesJokes: String,
        networkService: IChuckRepository,
        viewModel: JokeViewModel,
        request: (String, IChuckRepository, JokeViewModel) -> Disposable
    ): Disposable {
        return request(selectedCategoriesJokes, networkService, viewModel)
    }
}

