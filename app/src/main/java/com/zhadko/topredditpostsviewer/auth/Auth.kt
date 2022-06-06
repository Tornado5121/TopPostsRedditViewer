package com.zhadko.topredditpostsviewer.auth

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.rtt.CivicLocationKeys.STATE
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.zhadko.topredditpostsviewer.data.network.Requests
import kotlinx.coroutines.flow.MutableStateFlow

class Auth(
    private val context: Context,
    private val api: Requests
) {

    private val mAuthTokenFlow = MutableStateFlow("")
    val authTokenFlow = mAuthTokenFlow

    private val url: String = java.lang.String.format(
        MyAuthRes.AUTH_URL,
        MyAuthRes.CLIENT_ID,
        MyAuthRes.STATE,
        MyAuthRes.REDIRECT_URI
    )

    fun launchBrowserForLogin() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }

    fun getData(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            if (uri!!.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e(TAG, "An error has occurred : $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == STATE.toString()) {
                    val code = uri.getQueryParameter("code")
                    if (code != null) {
                        getAccessToken(code)
                    }
                }
            }
        }
    }

    private fun getAccessToken(code: String) {
        val authString = "$MyAuthRes.CLIENT_ID:${MyAuthRes.STATE}"
        val encodedAuthString = Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
        val accessToken = api.getToken(
            "Basic $encodedAuthString",
            "authorization_code",
            code,
            MyAuthRes.REDIRECT_URI
        ).access_token
        access_token = accessToken
        mAuthTokenFlow.value = access_token
    }

    companion object {
        var access_token = ""
    }

}