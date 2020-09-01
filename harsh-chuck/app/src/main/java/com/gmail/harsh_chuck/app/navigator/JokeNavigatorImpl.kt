package com.gmail.harsh_chuck.app.navigator

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.gmail.harsh_chuck.R
import javax.inject.Inject

class JokeNavigatorImpl @Inject constructor(private val activity: FragmentActivity) :
    JokeNavigator {

    override fun navigateTo(screen: JokeScreens) {
        val fragment: Int = when (screen) {
            JokeScreens.JOKE -> R.id.jokeFragment
        }
        activity.findNavController(R.id.joke_host).navigate(fragment)
    }

}