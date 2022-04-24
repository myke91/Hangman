package com.myke.hangman.feature.basic.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myke.hangman.feature.basic.ui.game.GameRoute
import com.myke.hangman.feature.basic.ui.game.GameViewModel
import com.myke.hangman.feature.basic.ui.intro.IntroRoute

@Composable
fun HangmanNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: HangmanNavigationActions,
    startDestination: String = HangmanDestinations.INTRO_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(HangmanDestinations.INTRO_ROUTE) {
            IntroRoute(navigationActions.navigateToGame)
        }
        composable(HangmanDestinations.GAME_ROUTE) {
            val viewModel: GameViewModel = hiltViewModel()
            GameRoute(viewModel)
        }
    }
}
