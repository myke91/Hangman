package com.myke.hangman

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideScoreManager(scoreCardDao: ScoreCardDao) = ScoreManager(scoreCardDao)
}