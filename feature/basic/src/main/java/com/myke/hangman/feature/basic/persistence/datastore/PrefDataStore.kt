package com.myke.hangman.feature.basic.persistence.datastore


interface PrefDataStore {
    suspend fun setUsedWords(usedWords: String)
}