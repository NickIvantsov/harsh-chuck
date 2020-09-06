package com.gmail.harsh_chuck.app.navigator

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.gmail.harsh_chuck.R
import javax.inject.Inject

/**
 * Navigator implementation.
 */
class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {

    override fun navigateTo(screen: Screens) {
        val fragment: Int = when (screen) {
            Screens.MAIN -> R.id.mainFragment
            Screens.JOKE_BY_CATEGORY -> R.id.jokeByCategoryFragment
            Screens.JOKE -> R.id.jokeFragment
            Screens.NEW_RANDOM_JOKE -> R.id.newRandomJokeFragment
        }
        activity.findNavController(R.id.my_nav_host_fragment).navigate(fragment)
    }

}