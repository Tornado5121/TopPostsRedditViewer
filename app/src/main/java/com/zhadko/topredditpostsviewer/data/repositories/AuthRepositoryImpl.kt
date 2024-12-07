package com.zhadko.topredditpostsviewer.data.repositories

import android.content.Intent
import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val auth: Auth
) : AuthRepository {

    override val authFlow = auth.authTokenFlow

    override fun login() {
        auth.launchBrowserForLogin()
    }

    override fun getData(intent: Intent) {
        auth.getData(intent)
    }

}