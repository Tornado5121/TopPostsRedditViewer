package com.zhadko.topredditpostsviewer.data.repositories

import android.content.Intent
import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.data.dataSource.dataStore.UserDataStore
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val auth: Auth,
    private val userDataStore: UserDataStore,
) : AuthRepository {

    override val authFlow: Flow<String>
        get() = userDataStore.getAccessToken()

    override fun login() {
        auth.launchBrowserForLogin()
    }

    override suspend fun getData(intent: Intent) {
        withContext(Dispatchers.IO) { auth.getData(intent) }
    }
}