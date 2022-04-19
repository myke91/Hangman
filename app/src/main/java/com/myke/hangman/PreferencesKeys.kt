package com.myke.hangman

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USED_WORDS = stringPreferencesKey("com.myke.hangman_used_words")
}