package com.myke.hangman

import kotlinx.coroutines.flow.Flow

interface PrefDataStore {
    suspend fun setUsedWords(usedWords: String)
}