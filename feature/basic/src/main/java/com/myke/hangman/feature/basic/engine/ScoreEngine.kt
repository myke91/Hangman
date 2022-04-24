package com.myke.hangman.feature.basic.engine

import com.myke.hangman.feature.basic.persistence.database.ScoreCard
import com.myke.hangman.feature.basic.persistence.database.ScoreCardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScoreEngine @Inject constructor(val scoreCardDao: ScoreCardDao) {

    fun pointsGained(word: String): Int {
        return if (word.length > 10) {
            5
        } else {
            2
        }
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