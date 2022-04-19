package com.myke.hangman


sealed class GameState {
    class Running(
        val lettersUsed: String,
        val underscoreWord: String,
        val drawable: Int,
        val remainingWords: Int,
        val remainingAttempts: Int
    ) : GameState()

    class Lost(val wordToGuess: String, val remainingAttempts: Int) : GameState()
    class Won(val wordToGuess: String) : GameState()
}
