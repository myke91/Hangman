package com.myke.hangman

import com.myke.hangman.engine.ScoreEngine
import com.myke.hangman.persistence.database.ScoreCardDao_Impl
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Test

class ScoreEngineTest {
    private val mockScoreDao: ScoreCardDao_Impl = mock()

    @Test
    fun `pointsGained() gives correct points for short word guessed`() {
        val wordToGuess = "Canada"
        val scoreEngine = ScoreEngine(mockScoreDao)
        val points = scoreEngine.pointsGained(wordToGuess)

        assertEquals(2, points)
    }

    @Test
    fun `pointsGained() gives correct points for long word guessed`() {
        val wordToGuess = "British/Indian/Ocean/Territory"
        val scoreEngine = ScoreEngine(mockScoreDao)
        val points = scoreEngine.pointsGained(wordToGuess)

        assertEquals(5, points)
    }

}