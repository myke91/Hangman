package com.myke.hangman.di

import com.myke.hangman.engine.GameEngine
import com.myke.hangman.persistence.database.ScoreCardDao
import com.myke.hangman.engine.ScoreEngine
import com.myke.hangman.interactors.TimestampConverterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//import dagger.hilt.testing.TestInstallIn

//@TestInstallIn(components = [SingletonComponent::class],
//    replaces = [AppModule::class])
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideScoreEngine(scoreCardDao: ScoreCardDao) = ScoreEngine(scoreCardDao)

    @Provides
    fun provideGameEngine() = GameEngine()

    @Provides
    fun provideTimestampConverterUseCase() = TimestampConverterUseCase()
}