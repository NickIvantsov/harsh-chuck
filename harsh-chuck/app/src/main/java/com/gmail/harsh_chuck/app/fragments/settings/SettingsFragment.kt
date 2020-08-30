package com.gmail.harsh_chuck.app.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.adapters.JokesCategoriesAdapter
import com.gmail.harsh_chuck.network.NetworkService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    val networkService = NetworkService
    lateinit var adapter: JokesCategoriesAdapter
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_jokes_categories)
        context?.let {
            adapter = JokesCategoriesAdapter(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        makeJokesCategoriesRequest(networkService)
    }

    private fun makeJokesCategoriesRequest(networkService: NetworkService) {
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