package com.gmail.harsh_chuck.app.fragments.jokeByCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.gmail.harsh_chuck.R
import com.gmail.harsh_chuck.app.navigator.AppNavigator
import com.gmail.harsh_chuck.app.navigator.JokeNavigator
import com.gmail.harsh_chuck.app.navigator.JokeScreens
import com.gmail.harsh_chuck.app.navigator.popBackStack
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
    private val btnOkPressed = { btn: Button,
                                 jokeCategoryLiveData: MutableLiveData<Boolean>,
                                 jokeCategoryLiveDataValue: Boolean,
                                 errorClick: (Throwable) -> Unit,
                                 jokeNavigator: JokeNavigator,
                                 jokeScreens: JokeScreens ->
        btn.clicks()
            .subscribe({
                jokeCategoryLiveData.value = jokeCategoryLiveDataValue
                jokeNavigator.navigateTo(jokeScreens)
            }, errorClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_by_categore_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPressed(
            btn_back,
            ((context?.applicationContext) as AppController).jokeCategoryLiveData,
            findNavController(), errorLog, popBackStack
        )



        btnOkPressed(
            btn_ok,
            ((context?.applicationContext) as AppController).jokeCategoryLiveData,
            false,
            errorLog,
            jokeNavigator,
            JokeScreens.JOKE
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ((context?.applicationContext) as AppController).jokeCategoryLiveData.observe(
            viewLifecycleOwner
        ) { result ->
            Observable.just(result)
                .subscribe({ isActive ->
                    when {
                        isActive -> {
                            enableBtn(btn_ok)
                        }
                        else -> {
                            disableBtn(btn_ok)
                        }
                    }
                }, errorLog)
        }
    }

    private val backPressed = { btn: Button,
                                jokeCategoryLiveData: MutableLiveData<Boolean>,
                                navController: NavController,
                                errorClick: (Throwable) -> Unit,
                                popBackStack: (NavController) -> Boolean ->
        btn.clicks()
            .subscribe({
                jokeCategoryLiveData.value = false
                popBackStack(navController)
            }, errorClick)
    }
}