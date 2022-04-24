package com.myke.hangman.feature.basic.di

import com.myke.hangman.feature.basic.engine.GameEngine
import com.myke.hangman.feature.basic.persistence.database.ScoreCardDao
import com.myke.hangman.feature.basic.engine.ScoreEngine
import com.myke.hangman.feature.basic.interactors.TimestampConverterUseCase
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