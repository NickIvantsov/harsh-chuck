package com.gmail.harsh_chuck.app.fragments.categoryJokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.adapters.RadioAdapter
import com.gmail.harsh_chuck.domain.AppController
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class CategoryJokesFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryJokesFragment()
    }

    @Inject
    lateinit var chuckRepository: IChuckRepository

    @Inject
    lateinit var radioAdapter: RadioAdapter<String>

    @Inject
    lateinit var jokesLiveData: MutableLiveData<String>

    @Inject
    lateinit var linearLayoutManager: Provider<LinearLayoutManager>

    private val viewModel: CategoryJokesViewModel by viewModels()

    private val makeJokesCategoriesRequest: (
        CategoryJokesViewModel,
        IChuckRepository,
        MutableLiveData<String>,
        (Throwable) -> Unit
    ) -> Disposable =
        { viewModel: CategoryJokesViewModel, networkService: IChuckRepository,
          liveData: MutableLiveData<String>,
          error: (Throwable) -> Unit ->
            viewModel.makeJokesCategoriesRequest(networkService, liveData, error)
        }

    private val jokesCategoriesLiveData =
        { jokesLiveData: MutableLiveData<String>,
          viewLifecycleOwner: LifecycleOwner,
          adapter: RadioAdapter<String>,
          error: (Throwable) -> Unit ->
            jokesLiveData.observe(viewLifecycleOwner) { categories ->
                Observable
                    .just(categories)
                    .subscribe({
                        adapter.addItem(it)
                    }, error)
            }
        }

    private val errorLog = errorTimber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_jokes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeJokesCategoriesRequest(viewModel, chuckRepository, jokesLiveData, errorLog)
        jokesCategoriesLiveData(jokesLiveData, viewLifecycleOwner, radioAdapter, errorLog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_categories).apply {
            adapter = radioAdapter
            layoutManager = linearLayoutManager.get()
        }


    }

}


