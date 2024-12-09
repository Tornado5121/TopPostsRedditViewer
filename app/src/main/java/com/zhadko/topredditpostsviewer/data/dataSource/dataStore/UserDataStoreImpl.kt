package com.zhadko.topredditpostsviewer.data.dataSource.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreImpl(
    private val context: Context,
) : UserDataStore {

    private val accessToken = stringPreferencesKey("accessToken")

    override suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { userPrefs ->
            userPrefs[accessToken] = token
        }
    }

    override fun getAccessToken(): Flow<String> {
        return context.dataStore.data.map { userPrefs ->
            userPrefs[accessToken] ?: ""
        }
    }
}

val Context.dataStore by preferencesDataStore(name = "userPrefs")