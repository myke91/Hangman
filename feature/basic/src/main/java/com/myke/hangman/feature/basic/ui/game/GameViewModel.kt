package com.myke.hangman.feature.basic.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myke.hangman.feature.basic.R
import com.myke.hangman.feature.basic.engine.GameEngine
import com.myke.hangman.feature.basic.engine.ScoreEngine
import com.myke.hangman.feature.basic.interactors.TimestampConverterUseCase
import com.myke.hangman.feature.basic.model.GameState
import com.myke.hangman.feature.basic.persistence.datastore.PrefDataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameEngine: GameEngine,
    private val scoreEngine: ScoreEngine,
    private val prefDataStoreImpl: PrefDataStoreImpl,
    private val usecase: TimestampConverterUseCase
) : ViewModel() {

    private var _word = MutableStateFlow("_ _ A B C D _ _ _ _ _ _ _ ")
    val word get() = _word.asStateFlow()

    private var _lettersUsed = MutableStateFlow("Used Letters: A, B, C, D, E, F, G")
    val lettersUsed get() = _lettersUsed.asStateFlow()

    private var _image = MutableStateFlow(R.drawable.game0)
    val image get() = _image.asStateFlow()

    private var _gameWon = MutableStateFlow(false)
    val gameWon get() = _gameWon.asStateFlow()

    private var _remainingWords = MutableStateFlow(0)
    val remainingWords get() = _remainingWords.asStateFlow()

    private var _remainingAttempts = MutableStateFlow(0)
    val remainingAttempts get() = _remainingAttempts.asStateFlow()

    private var _enableWordEntry = MutableStateFlow(false)
    val enableWordEntry get() = _enableWordEntry.asStateFlow()

    private var _gameCompleted = MutableStateFlow(false)
    val gameCompleted get() = _gameCompleted.asStateFlow()

    private var _pointsGained = MutableStateFlow(0)
    val pointsGained get() = _pointsGained.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog get() = _showDialog.asStateFlow()

    private val _showScoreHistory = MutableStateFlow(false)
    val showScoreHistory get() = _showScoreHistory.asStateFlow()

    private val _scoreHistory = MutableStateFlow("")
    val scoreHistory get() = _scoreHistory.asStateFlow()

    fun startGame() {
        initializeGame()
    }


    private fun initializeGame() {
        CoroutineScope(Dispatchers.Main + Job()).launch {
            val wordIndices = prefDataStoreImpl.savedUsedWords.first()

            val splitWordIndices = wordIndices.split(",")
            val usedWords = mutableListOf<Int>()
            for (s in splitWordIndices) {
                if (s.isNotEmpty()) {
                    if (!usedWords.contains(s.toInt())) {
                        usedWords.add(s.toInt())
                    }
                }
            }

            val gameState = gameEngine.startNewGame(usedWords)
            _gameWon.value = false
            _enableWordEntry.value = true
            _gameCompleted.value = false
            _showScoreHistory.value = false

            updateUiState(gameState.first)
            updateUsedWords(gameState.second)

            cancel()
        }
    }

    private fun updateUsedWords(index: Int) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            val wordIndices = prefDataStoreImpl.savedUsedWords.first()
            val newString = "$wordIndices,$index"
            prefDataStoreImpl.setUsedWords(newString)
            cancel()
        }
    }


    fun play(letters: String) {
        val gameState = gameEngine.play(letters.last())
        updateUiState(gameState)
    }

    private fun updateUiState(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> {
                showGameLost(gameState.wordToGuess)
                _remainingAttempts.value = gameState.remainingAttempts
                _gameCompleted.value = true
            }
            is GameState.Running -> {
                _remainingWords.value = gameState.remainingWords
                _remainingAttempts.value = gameState.remainingAttempts
                _word.value = gameState.underscoreWord
                _lettersUsed.value = gameState.lettersUsed
                _image.value = gameState.drawable
            }
            is GameState.Won -> {
                _gameCompleted.value = true
                showGameWon(gameState.wordToGuess)
                processScore(gameState.wordToGuess)
            }
        }
    }


    private fun processScore(wordToGuess: String) {
        val points = scoreEngine.pointsGained(wordToGuess)
        _pointsGained.value = points
        _showDialog.value = true

        viewModelScope.launch {
            scoreEngine.updateScore(wordToGuess, points)
        }
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        viewScoreHistory()
    }

    fun viewScoreHistory() {
        _showDialog.value = false
        viewModelScope.launch {
            val scoreCardList = scoreEngine.getScoreHistory()
            val scoreCardBuilder = StringBuilder()
            for (score in scoreCardList) {
                scoreCardBuilder
                    .append(usecase.run(score.playedAt)).append("\n")
                    .append(score.wordGuessed).append("\n")
                    .append(score.pointsGained)
                    .append("\n\n")

            }

            _scoreHistory.value = scoreCardBuilder.toString()
            _showScoreHistory.value = true
            _showDialog.value = true
        }
    }

    private fun showGameLost(wordToGuess: String) {
        _word.value = wordToGuess
        _gameWon.value = false
        _image.value = R.drawable.game7
        _enableWordEntry.value = false
    }

    private fun showGameWon(wordToGuess: String) {
        _word.value = wordToGuess
        _gameWon.value = true
        _enableWordEntry.value = false
    }

}