package com.zhadko.topredditpostsviewer.data.dataSource.network.interceptors

import com.zhadko.topredditpostsviewer.data.dataSource.dataStore.UserDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val userDataStore: UserDataStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { userDataStore.getAccessToken().first() }
        val request =
            chain.request().newBuilder().addHeader("Authorization", accessToken).build()
        return chain.proceed(request)
    }
}