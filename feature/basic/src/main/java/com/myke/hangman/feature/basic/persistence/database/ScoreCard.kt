package com.myke.hangman.feature.basic.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScoreCard(
    @PrimaryKey
    val playedAt: Long,
    val wordGuessed: String,
    val pointsGained: Int
)