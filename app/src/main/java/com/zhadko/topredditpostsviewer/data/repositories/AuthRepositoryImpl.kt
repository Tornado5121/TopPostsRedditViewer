package com.zhadko.topredditpostsviewer.data.repositories

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.zhadko.topredditpostsviewer.data.dataSource.dataStore.UserDataStore
import com.zhadko.topredditpostsviewer.data.dataSource.network.Requests
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import com.zhadko.topredditpostsviewer.utils.MyAuthRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.Credentials

class AuthRepositoryImpl(
    private val userDataStore: UserDataStore,
    private val api: Requests,
    private val context: Context,
) : AuthRepository {

    private val url: String = java.lang.String.format(
        MyAuthRes.AUTH_URL,
        MyAuthRes.CLIENT_ID,
        MyAuthRes.STATE,
        MyAuthRes.REDIRECT_URI
    )

    override val authFlow: Flow<String>
        get() = userDataStore.getAccessToken()

    override fun login() = launchBrowserForLogin()

    override suspend fun getData(intent: Intent) {
        withContext(Dispatchers.IO) { getToken(intent) }
    }

    private fun launchBrowserForLogin() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }

    private suspend fun getToken(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = Uri.parse(intent.data.toString())
            val code = uri.getQueryParameter("code")
            if (code == null) {
                val error = uri.getQueryParameter("error")
                Log.e(TAG, "An error has occurred : $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == "MY_RANDOM_STRING_1") {
                    getAccessToken(code)
                }
            }
        }
    }

    private suspend fun getAccessToken(code: String) {
        val accessToken = api.getToken(
            Credentials.basic(
                MyAuthRes.CLIENT_ID, ""
            ),
            "authorization_code",
            code,
            MyAuthRes.REDIRECT_URI
        ).access_token
        userDataStore.saveAccessToken(accessToken)
    }
}