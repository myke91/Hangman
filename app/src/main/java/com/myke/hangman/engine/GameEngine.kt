package com.myke.hangman.engine

import com.myke.hangman.R
import com.myke.hangman.model.GameConstants
import com.myke.hangman.model.GameState
import com.myke.hangman.persistence.datastore.PrefDataStoreImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.random.Random

class GameEngine {
    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = 7
    private var currentTries = 0
    private var drawable: Int = R.drawable.game0
    private var wordCount = listOf<Int>()

    fun startNewGame(usedWords: List<Int>): Pair<GameState, Int> {
        lettersUsed = ""
        currentTries = 0
        var randomIndex: Int
        while (true) {
            randomIndex = Random.nextInt(0, GameConstants.words.size)
            if (!usedWords.contains(randomIndex)) {
                break
            }
        }
        wordCount = usedWords
        wordToGuess = GameConstants.words[randomIndex]
        underscoreWord = generateUnderscores(wordToGuess)
        return Pair(getGameState(), randomIndex)
    }


    fun generateUnderscores(word: String): String {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
            }
        }
        return sb.toString()
    }

    fun play(letter: Char): GameState {
        if (lettersUsed.contains(letter)) {
            return GameState.Running(
                lettersUsed,
                underscoreWord,
                drawable,
                getRemainingWords(),
                getRemainingAttempts()
            )
        }

        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getGameState()
    }

    private fun getHangmanDrawable(): Int {
        return when (currentTries) {
            0 -> R.drawable.game0
            1 -> R.drawable.game1
            2 -> R.drawable.game2
            3 -> R.drawable.game3
            4 -> R.drawable.game4
            5 -> R.drawable.game5
            6 -> R.drawable.game6
            7 -> R.drawable.game7
            else -> R.drawable.game7
        }
    }

    private fun getGameState(): GameState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return GameState.Won(wordToGuess)
        }

        if (currentTries == maxTries) {
            return GameState.Lost(wordToGuess, getRemainingAttempts())
        }

        drawable = getHangmanDrawable()
        return GameState.Running(
            lettersUsed,
            underscoreWord,
            drawable,
            getRemainingWords(),
            getRemainingAttempts()
        )
    }

    private fun getRemainingWords(): Int {
        return GameConstants.words.size - wordCount.size
    }

    private fun getRemainingAttempts(): Int {
        return maxTries - currentTries
    }
}