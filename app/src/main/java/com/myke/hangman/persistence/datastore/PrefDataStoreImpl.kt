package com.myke.hangman.persistence.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context
) : PrefDataStore {
    private val STORE_NAME = "com.myke.hangman_data_store"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)

    override suspend fun setUsedWords(usedWords: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USED_WORDS] = usedWords
        }
    }

    val savedUsedWords: Flow<String> = context.dataStore.data
        .map { preferences ->
            val words = preferences[PreferencesKeys.USED_WORDS] ?: ""
            words
        }
}