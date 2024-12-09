package com.zhadko.topredditpostsviewer.domain.repositories

import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthRepository {

    val authFlow: Flow<String>

    fun login()
    suspend fun getData(intent: Intent)

}