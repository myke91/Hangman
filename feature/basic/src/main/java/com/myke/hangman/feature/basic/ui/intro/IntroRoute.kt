package com.myke.hangman.feature.basic.ui.intro

import androidx.compose.runtime.Composable

@Composable
fun IntroRoute(navigateToGame: () -> Unit) {
    IntroScreen(navigateToGame)
}
