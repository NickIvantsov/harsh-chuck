package com.gmail.harsh_chuck.app.navigator

/**
 * Available screens.
 */
enum class Screens {
    MAIN,
    JOKE_BY_CATEGORY,
    JOKE
}

/**
 * Interfaces that defines an app navigator.
 */
interface AppNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: Screens)
}