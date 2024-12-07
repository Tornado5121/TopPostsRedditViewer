package com.zhadko.topredditpostsviewer.domain.repositories

import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthRepository {

    val authFlow: MutableStateFlow<String>

    fun login()
    fun getData(intent: Intent)

}