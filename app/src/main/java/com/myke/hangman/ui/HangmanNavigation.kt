package com.myke.hangman.ui

import androidx.navigation.NavHostController


object HangmanDestinations {
    const val INTRO_ROUTE = "intro"
    const val GAME_ROUTE = "game"
}

/**
 * Models the navigation actions in the app.
 */
class HangmanNavigationActions(navController: NavHostController) {
    val navigateToIntro: () -> Unit = {
        navController.navigate(HangmanDestinations.INTRO_ROUTE)
    }
    val navigateToGame: () -> Unit = {
        navController.navigate(HangmanDestinations.GAME_ROUTE)
    }
}
