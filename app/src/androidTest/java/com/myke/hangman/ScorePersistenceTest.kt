package com.myke.hangman

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myke.hangman.feature.basic.persistence.database.HangmanDatabase
import com.myke.hangman.feature.basic.persistence.database.ScoreCard
import com.myke.hangman.feature.basic.persistence.database.ScoreCardDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ScorePersistenceTest : TestCase() {
    private lateinit var dao: ScoreCardDao
    private lateinit var db: HangmanDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, HangmanDatabase::class.java
        ).build()
        dao = db.scoreCardDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun testSaveScoreHistory() = runBlocking {
        val score1 = ScoreCard(
            0L,
            "Ghana",
            2
        )

        val score2 = ScoreCard(
            1L,
            "Central/African/Republic",
            5
        )

        dao.insertAll(listOf(score1, score2))

        val scores = dao.getAll()
        assertEquals(scores.size, 2)
    }

    @Test
    fun testTotalScorePoints() = runBlocking {
        val score1 = ScoreCard(
            0L,
            "Ghana",
            2
        )

        val score2 = ScoreCard(
            1L,
            "Central/African/Republic",
            5
        )

        dao.insertAll(listOf(score1, score2))

        val total = dao.getTotalPoints()
        assertEquals(total, 7)
    }
}