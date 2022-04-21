package com.myke.hangman.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myke.hangman.R
import com.myke.hangman.engine.GameEngine
import com.myke.hangman.engine.ScoreEngine
import com.myke.hangman.model.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameEngine: GameEngine,
    private val scoreEngine: ScoreEngine
) :
    ViewModel() {

    private var _word = MutableStateFlow<String>("_ _ A B C D _ _ _ _ _ _ _ ")
    val word get() = _word

    private var _lettersUsed = MutableStateFlow<String>("Used Letters: A, B, C, D, E, F, G")
    val lettersUsed get() = _lettersUsed

    private var _image = MutableStateFlow<Int>(R.drawable.game0)
    val image get() = _image

    private var _gameWon = MutableStateFlow<Boolean>(false)
    val gameWon get() = _gameWon

    private var _remainingWords = MutableStateFlow<Int?>(0)
    val remainingWords get() = _remainingWords

    private var _remainingAttempts = MutableStateFlow<Int?>(0)
    val remainingAttempts get() = _remainingAttempts

    private var _enableWordEntry = MutableStateFlow<Boolean?>(false)
    val enableWordEntry get() = _enableWordEntry

    private var _gameCompleted = MutableStateFlow<Boolean?>(false)
    val gameCompleted get() = _gameCompleted


    fun startGame() {
        val gameState = gameEngine.startNewGame()
        gameWon.value = false
        enableWordEntry.value = true
        _gameCompleted.value = false

        updateUiState(gameState)
    }

    fun play(letters: String) {
        val gameState = gameEngine.play(letters.last())
        updateUiState(gameState)
    }

    private fun updateUiState(gameState: GameState?) {
        when (gameState) {
            is GameState.Lost -> {
                showGameLost(gameState.wordToGuess)
                remainingAttempts.value = gameState.remainingAttempts
                _gameCompleted.value = true
            }
            is GameState.Running -> {
                remainingWords.value = gameState.remainingWords
                remainingAttempts.value = gameState.remainingAttempts
                word.value = gameState.underscoreWord
                lettersUsed.value = gameState.lettersUsed
                image.value = gameState.drawable
            }
            is GameState.Won -> {
                _gameCompleted.value = true
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
        gameWon.value = false
        image.value = R.drawable.game7
        enableWordEntry.value = false
    }

    private fun showGameWon(wordToGuess: String) {
        word.value = wordToGuess
        gameWon.value = true
        enableWordEntry.value = false
    }

}