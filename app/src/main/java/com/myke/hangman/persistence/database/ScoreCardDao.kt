package com.myke.hangman.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreCardDao {
    @Query("SELECT * FROM scorecard ORDER BY playedAt ASC")
    fun getAll(): List<ScoreCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(car: List<ScoreCard>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: ScoreCard)

    @Query("SELECT sum(pointsGained) from scorecard")
    suspend fun getTotalPoints(): Int
}