package com.zhadko.topredditpostsviewer.data.repositories.authRepository

import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthRepository {

    val authFlow: MutableStateFlow<String>

    fun login()
    fun getData(intent: Intent)

}