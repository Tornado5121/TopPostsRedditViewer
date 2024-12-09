package com.zhadko.topredditpostsviewer.data.repositories

import android.content.Intent
import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val auth: Auth,
) : AuthRepository {

    override val authFlow = auth.authTokenFlow

    override fun login() {
        auth.launchBrowserForLogin()
    }

    override suspend fun getData(intent: Intent) {
        withContext(Dispatchers.IO) { auth.getData(intent) }
    }

}