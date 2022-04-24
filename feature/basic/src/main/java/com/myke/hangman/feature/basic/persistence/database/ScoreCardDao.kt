package com.myke.hangman.feature.basic.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreCardDao {
    @Query("SELECT * FROM scorecard ORDER BY playedAt ASC")
    fun getAll(): List<ScoreCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scores: List<ScoreCard>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: ScoreCard)

    @Query("SELECT sum(pointsGained) from scorecard")
    suspend fun getTotalPoints(): Int
}