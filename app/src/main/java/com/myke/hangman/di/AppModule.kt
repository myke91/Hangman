package com.myke.hangman.di

import com.myke.hangman.engine.GameEngine
import com.myke.hangman.persistence.database.ScoreCardDao
import com.myke.hangman.engine.ScoreEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideScoreEngine(scoreCardDao: ScoreCardDao) = ScoreEngine(scoreCardDao)

    @Provides
    fun provideGameEngine() = GameEngine()
}