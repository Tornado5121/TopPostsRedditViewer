package com.zhadko.topredditpostsviewer.data.dataSource.dataStore

import kotlinx.coroutines.flow.Flow

interface UserDataStore {

    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String>
}