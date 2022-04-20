package com.myke.hangman.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScoreCard(
    @PrimaryKey
    val playedAt: Long,
    val wordGuessed: String,
    val pointsGained: Int
)