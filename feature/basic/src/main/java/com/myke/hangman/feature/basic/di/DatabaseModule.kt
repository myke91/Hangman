package com.myke.hangman.feature.basic.di

import android.content.Context
import androidx.room.Room
import com.myke.hangman.feature.basic.persistence.database.HangmanDatabase
import com.myke.hangman.feature.basic.persistence.database.ScoreCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideHangmanDatabase(@ApplicationContext appContext: Context): HangmanDatabase {
        return Room.databaseBuilder(
            appContext,
            HangmanDatabase::class.java,
            "com.myke.hangman_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideScoreCardDao(db: HangmanDatabase): ScoreCardDao = db.scoreCardDao()
}