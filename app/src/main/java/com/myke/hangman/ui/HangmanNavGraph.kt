
package com.myke.hangman.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myke.hangman.ui.game.GameRoute
import com.myke.hangman.ui.game.GameViewModel
import com.myke.hangman.ui.intro.IntroRoute

@Composable
fun HangmanNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HangmanDestinations.INTRO_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(HangmanDestinations.INTRO_ROUTE) {
            IntroRoute()
        }
        composable(HangmanDestinations.GAME_ROUTE) {
            val viewModel: GameViewModel by by viewModels()

            GameRoute(viewModel)
        }
    }
}
