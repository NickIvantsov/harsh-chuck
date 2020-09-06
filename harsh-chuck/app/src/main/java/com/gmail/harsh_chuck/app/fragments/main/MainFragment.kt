package com.gmail.harsh_chuck.app.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.Screens
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse
import com.gmail.harsh_chuck.domain.repository.IChuckRepository
import com.gmail.harsh_chuck.helpers.errorTimber
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
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

    @Inject
    lateinit var jokeLiveData: MutableLiveData<JokeRandomResponse>

    private val errorLog = errorTimber

    private val navigateTo = { navigator: AppNavigator, screen: Screens ->
        navigator.navigateTo(screen)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPressed(
            btn_joke_by_category,
            navigator,
            Screens.JOKE_BY_CATEGORY,
            navigateTo,
            errorLog
        )
        btnPressed(
            btn_new_random,
            navigator, Screens.NEW_RANDOM_JOKE,
            navigateTo,
            errorLog
        )
    }


    private fun btnPressed(
        btn: Button,
        appNavigator: AppNavigator,
        screen: Screens,
        navigator: (AppNavigator, Screens) -> Unit,
        errorClick: (Throwable) -> Unit
    ) {
        btn.clicks()
            .subscribe({
                navigator(appNavigator, screen)
            }, errorClick)
    }


}