package com.myke.hangman.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
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
        navController.navigate(HangmanDestinations.INTRO_ROUTE) {

            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToGame: () -> Unit = {
        navController.navigate(HangmanDestinations.GAME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
