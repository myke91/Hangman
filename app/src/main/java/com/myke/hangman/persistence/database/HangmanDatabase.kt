package com.myke.hangman.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(ScoreCard::class), version = 1)
abstract class HangmanDatabase: RoomDatabase() {
    abstract fun scoreCardDao(): ScoreCardDao
}