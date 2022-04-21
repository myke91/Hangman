package com.myke.hangman.persistence.datastore


interface PrefDataStore {
    suspend fun setUsedWords(usedWords: String)
}