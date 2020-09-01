package com.gmail.harsh_chuck.app.navigator

/**
 * Available screens.
 */
enum class Screens {
    MAIN,
    SETTINGS
}

/**
 * Interfaces that defines an app navigator.
 */
interface AppNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: Screens)
}