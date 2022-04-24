package com.myke.hangman

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GamePlayTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            HangmanApp()
        }
    }

    private fun navigateToGameScreen() {
        composeTestRule.onNodeWithText("Play").performClick()
    }

    @Test
    fun introScreenHasPlayButton() {
        composeTestRule.onNodeWithText("Play").assertIsDisplayed()
    }

    @Test
    fun launchGameScreenWhenClickOnPlayButton() {
        composeTestRule.onNodeWithText("Play").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Start New Game").assertIsDisplayed()
    }

    @Test
    fun gameScreenHasStartNewGameButton() {
        navigateToGameScreen()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Start New Game").assertIsDisplayed()
    }

    @Test
    fun startNewGameWhenClickStartNewGameButton() {
        navigateToGameScreen()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Start New Game").performClick()
    }

    @Test
    fun gameScreenHasTextField() {
        //TODO: implement
    }

    @Test
    fun gameScreenHasHangmanImage() {
        //TODO: implement
    }

    @Test
    fun gameScreenWordToGuess() {
        //TODO: implement
    }

    @Test
    fun gameScreenShowsRemainingUniqueWords() {
        //TODO: implement
    }

    @Test
    fun gameScreenShowsRemainingAttempts() {
        //TODO: implement
    }
}