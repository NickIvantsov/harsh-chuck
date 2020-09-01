package com.gmail.harsh_chuck.app.fragments.jokeByCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.adapters.RadioAdapter
import com.gmail.harsh_chuck.network.INetworkService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class JokeByCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = JokeByCategoryFragment()
    }

    @Inject
    lateinit var networkService: INetworkService

    @Inject
    lateinit var adapter: RadioAdapter<String>
    private val viewModel: JokeByCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_by_categore_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeJokesCategoriesRequest()
        jokesCategoriesLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* context?.let {
             adapter = CategoriesJokesAdapter(it)
         }*/
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_categories)
        context?.let {
            recyclerView.adapter = adapter
        }

        recyclerView.layoutManager = LinearLayoutManager(context)


    }

    private fun makeJokesCategoriesRequest() {
        viewModel.makeJokesCategoriesRequest(networkService)
    }

    private fun jokesCategoriesLiveData() {
        viewModel.jokesCategoriesLiveData.observe(viewLifecycleOwner) { categories ->
            Observable
                .just(categories)
                .subscribe({
                    adapter.addItem(it)
                }) {
                    errorLog(it)
                }
        }
    }

    private fun errorLog(throwable: Throwable) {
        Timber.e(throwable)
    }
}