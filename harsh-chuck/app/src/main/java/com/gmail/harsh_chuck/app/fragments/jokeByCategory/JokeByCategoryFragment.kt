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
import com.jakewharton.rxbinding.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.joke_by_categore_fragment.*
import timber.log.Timber
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
                ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = true
                jokeNavigator.navigateTo(JokeScreens.JOKE)
            }) {
                errorLog(it)
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ((context?.applicationContext) as AppController).jokeCategoryLiveData.observe(
            viewLifecycleOwner
        ) { result ->
            Observable.just(result)
                .subscribe({
                    when {
                        it -> {
                            setActiveOkBtn(0.3F, false)
                        }
                        else -> {
                            setActiveOkBtn(1F, true)
                        }
                    }
                }) {
                    errorLog(it)
                }
        }
    }

    private fun setActiveOkBtn(alpha: Float, isClickable: Boolean) {
        btn_ok.alpha = alpha
        btn_ok.isClickable = isClickable
    }

    private fun backPressed() {
        btn_back.clicks()
            .subscribe({
                findNavController().popBackStack()
            }) {
                errorLog(it)
            }
    }


    private fun errorLog(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun onDestroy() {
        ((context?.applicationContext) as AppController).jokeCategoryLiveData.value = false
        super.onDestroy()
    }
}