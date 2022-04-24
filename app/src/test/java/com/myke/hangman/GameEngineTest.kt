package com.myke.hangman

import com.myke.hangman.engine.GameEngine
import com.myke.hangman.model.GameState
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Test

class GameEngineTest {

    @Test
    fun `generateUnderscores to return correct amount of underscores`() {
        val gameEngine = GameEngine()


        val word1 = "England"
        val word2 = "Spain"
        val word3 = "Italy"
        val word4 = "Saudi/Arabia"

        val underscores1 = "_______"
        val underscores2 = "_____"
        val underscores3 = "_____"
        val underscores4 = "_____/______"



        val actual1 = gameEngine.generateUnderscores(word1)
        val actual2 = gameEngine.generateUnderscores(word2)
        val actual3 = gameEngine.generateUnderscores(word3)
        val actual4 = gameEngine.generateUnderscores(word4)


        assertEquals(underscores1, actual1)
        assertEquals(underscores2, actual2)
        assertEquals(underscores3, actual3)
        assertEquals(underscores4, actual4)
    }

    @Test
    fun `starting game should only return unique words`() {
        val gameEngine = GameEngine()


        val usedWords = listOf(1, 2, 3, 5)


        val result = gameEngine.startNewGame(usedWords)
        val wordIndex = result.second


        assertFalse(usedWords.contains(wordIndex))
    }

    @Test
    fun `try attempts is computed correctly`() {
        val gameEngine = GameEngine()
        val usedWords = listOf(1, 2, 3, 5)
        gameEngine.startNewGame(usedWords)


        var gameState = gameEngine.play('4')
        var attempts = (gameState as GameState.Running).remainingAttempts


        assertEquals(6, attempts)


        gameState = gameEngine.play('3')
        attempts = (gameState as GameState.Running).remainingAttempts


        assertEquals(5, attempts)


        gameState = gameEngine.play('4')
        attempts = (gameState as GameState.Running).remainingAttempts


        assertEquals(5, attempts)
    }
}