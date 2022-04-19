package com.myke.hangman

import android.content.Context
import androidx.room.Room
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