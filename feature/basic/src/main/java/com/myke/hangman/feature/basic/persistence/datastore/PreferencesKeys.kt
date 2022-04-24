package com.myke.hangman.feature.basic.persistence.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USED_WORDS = stringPreferencesKey("com.myke.hangman_used_words")
}