package com.gmail.harsh_chuck.app.fragments.jokeByCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.JokeNavigator
import com.gmail.harsh_chuck.app.navigator.JokeScreens
import com.gmail.harsh_chuck.domain.AppController
import com.gmail.harsh_chuck.helpers.disableBtn
import com.gmail.harsh_chuck.helpers.enableBtn
import com.gmail.harsh_chuck.helpers.errorTimber
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.joke_by_categore_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class JokeByCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = JokeByCategoryFragment()
    }

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var jokeNavigator: JokeNavigator

    private val errorLog = errorTimber

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_by_categore_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPressed()
        btn_ok.clicks()
            .subscribe({
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
                jokeNavigator.navigateTo(JokeScreens.JOKE)
            }, errorLog)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ((context?.applicationContext) as AppController).jokeCategoryLiveData.observe(
            viewLifecycleOwner
        ) { result ->
            Observable.just(result)
                .subscribe({ isActeve ->
                    when {
                        isActeve -> {
                            enableBtn(btn_ok)
                        }
                        else -> {
                            disableBtn(btn_ok)
                        }
                    }
                }, errorLog)
        }
    }

    private fun backPressed() {
        btn_back.clicks()
            .subscribe({
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
                findNavController().popBackStack()
            }, errorLog)
    }
}