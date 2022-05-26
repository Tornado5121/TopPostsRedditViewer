package com.zhadko.topredditpostsviewer.data.network

import com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts.TopRedditPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface Requests {

    @GET("top.json")
    suspend fun getTopPostsNewPage(@Query("after") postIdAfter: String): TopRedditPosts

    @GET("top.json")
    suspend fun getTopPosts(): TopRedditPosts

}