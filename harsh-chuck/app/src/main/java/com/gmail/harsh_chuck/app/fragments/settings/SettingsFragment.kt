package com.gmail.harsh_chuck.app.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.adapters.JokesCategoriesAdapter
import com.gmail.harsh_chuck.network.INetworkService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    @Inject
    lateinit var networkService: INetworkService

    @Inject
    lateinit var adapter: JokesCategoriesAdapter

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_jokes_categories)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        makeJokesCategoriesRequest(networkService)
    }

    private fun makeJokesCategoriesRequest(networkService: INetworkService) {
        networkService.getChuckApi().jokesCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it?.replace("[", "")

            }.map {
                it?.replace("]", "")
            }
            .map {
                it?.replace("\"", "")
            }
            .map {
                it?.split(",") ?: ArrayList()
            }
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .subscribe({ responseItem ->
                adapter.add(responseItem)
                Timber.d("$responseItem")
            }) {
                Timber.e(it)
            }
    }
}