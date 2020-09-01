package com.gmail.harsh_chuck.app.navigator

enum class JokeScreens {
    JOKE
}

/**
 * Interfaces that defines an app navigator.
 */
interface JokeNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: JokeScreens)
}