package com.myke.hangman.ui.intro

import androidx.compose.runtime.Composable

@Composable
fun IntroRoute(navigateToGame: () -> Unit) {
    IntroScreen(navigateToGame)
}
