package com.myke.hangman.persistence.datastore

import kotlinx.coroutines.flow.Flow

interface PrefDataStore {
    suspend fun setUsedWords(usedWords: String)
}