package com.zhadko.topredditpostsviewer.data.network

import com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts.TopRedditPosts
import retrofit2.http.GET

interface Requests {

    @GET("top.json")
    suspend fun getTopPosts(): TopRedditPosts

}