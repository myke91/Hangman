package com.myke.hangman.engine

import com.myke.hangman.persistence.database.ScoreCard
import com.myke.hangman.persistence.database.ScoreCardDao
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