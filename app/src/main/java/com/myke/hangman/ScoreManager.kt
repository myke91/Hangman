package com.myke.hangman

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ScoreManager @Inject constructor(val scoreCardDao: ScoreCardDao) {

    fun pointsGained(word: String): Int {
        return if (word.length > 10) {
            5
        } else {
            2
        }
    }

    fun saveUsedWord(){

    }

    suspend fun getScoreHistory(): List<ScoreCard> {
       return withContext(Dispatchers.IO) {
            scoreCardDao.getAll()
        }
    }

    suspend fun updateScore(wordGuessed: String, points: Int) {
        withContext(Dispatchers.IO) {
            val scoreCard = ScoreCard(
                System.currentTimeMillis(),
                wordGuessed,
                points
            )
            scoreCardDao.insert(
                scoreCard
            )
        }
    }

}