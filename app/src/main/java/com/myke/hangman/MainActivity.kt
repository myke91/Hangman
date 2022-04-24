package com.myke.hangman

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.myke.hangman.feature.basic.ui.HangmanNavGraph
import com.myke.hangman.feature.basic.ui.HangmanNavigationActions
import com.myke.simplecompose.ui.theme.HangmanComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HangmanApp()
        }
    }
}

@Composable
fun HangmanApp() {
    HangmanComposeTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            HangmanNavigationActions(navController)
        }

        HangmanNavGraph(navController = navController,
            navigationActions = navigationActions)
    }
}
