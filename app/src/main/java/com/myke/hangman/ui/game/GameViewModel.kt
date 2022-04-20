package com.myke.hangman.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myke.hangman.R
import com.myke.hangman.engine.GameEngine
import com.myke.hangman.engine.ScoreEngine
import com.myke.hangman.model.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor(val gameEngine: GameEngine, val scoreEngine: ScoreEngine) :
    ViewModel() {
    private var _gameState = MutableStateFlow<GameState?>(null)

    private var _word = MutableStateFlow<String>("_ _ A B C D _ _ _ _ _ _ _ ")
    val word get() = _word

    private var _lettersUsed = MutableStateFlow<String>("Used Letters: A, B, C, D, E, F, G")
    val lettersUsed get() = _lettersUsed

    private var _image = MutableStateFlow<Int>(-1)
    val image get() = _image

    private var _gameLost = MutableStateFlow<Boolean>(false)
    val gameLost get() = _gameLost

    private var _gameWon = MutableStateFlow<Boolean>(false)
    val gameWon get() = _gameWon

    private var _remainingWords = MutableStateFlow<Int?>(null)
    val remainingWords get() = _remainingWords

    private var _remainingAttempts = MutableStateFlow<Int?>(null)
    val remainingAttempts get() = _remainingAttempts

    private var _lettersLayout = MutableStateFlow<Boolean?>(false)
    val lettersLayout get() = _lettersLayout


    fun startGame() {
        _gameState.value = gameEngine.startNewGame()
        gameLost.value = false
        gameWon.value = false
        lettersLayout.value = true

        _gameState.value.let {
            updateUiState(it)
        }
    }

    fun play(letter: Char) {
        val gameState = gameEngine.play(letter)
        updateUiState(gameState)
    }

    private fun updateUiState(gameState: GameState?) {
        when (gameState) {
            is GameState.Lost -> {
                showGameLost(gameState.wordToGuess)
                remainingAttempts.value = gameState.remainingAttempts
            }
            is GameState.Running -> {
                remainingWords.value = gameState.remainingWords
                remainingAttempts.value = gameState.remainingAttempts
                word.value = gameState.underscoreWord
                lettersUsed.value = gameState.lettersUsed
                image.value = gameState.drawable
            }
            is GameState.Won -> {
                showGameWon(gameState.wordToGuess)
                showCurrentScore(gameState.wordToGuess)
            }
        }
    }


    private fun showCurrentScore(wordToGuess: String) {
        val points = scoreEngine.pointsGained(wordToGuess)
        val scoreMessage = "Hurray!!!! You gained $points points"

        viewModelScope.launch {
            scoreEngine.updateScore(wordToGuess, points)
        }
    }

    private fun showGameLost(wordToGuess: String) {
        word.value = wordToGuess
        gameLost.value = true
        image.value = R.drawable.game7
        lettersLayout.value = false
    }

    private fun showGameWon(wordToGuess: String) {
        word.value = wordToGuess
        gameWon.value = true
        lettersLayout.value = false
    }

}