package com.gmail.harsh_chuck.app.navigator

import androidx.navigation.NavController

val popBackStack: (NavController) -> Boolean = { navController: NavController ->
    navController.popBackStack()
}