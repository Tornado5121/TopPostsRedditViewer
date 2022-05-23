package com.zhadko.topredditpostsviewer.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.reddit.com/"

object RetrofitClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: Requests by lazy {
        retrofit.create(Requests::class.java)
    }

}