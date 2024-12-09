package com.zhadko.topredditpostsviewer.data.dataSource.network

import com.zhadko.topredditpostsviewer.data.dataSource.network.dto.TopRedditPosts
import com.zhadko.topredditpostsviewer.data.dataSource.network.dto.auth.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Requests {

    @GET("top.json")
    suspend fun getTopPostsNewPage(
        @Query("after") postIdAfter: String,
    ): TopRedditPosts

    @GET("top.json")
    suspend fun getTopPosts(): TopRedditPosts

    @FormUrlEncoded
    @POST("api/v1/access_token")
    suspend fun getToken(
        @Header("Authorization") Authorization: String?,
        @Field("grant_type") grant_type: String?,
        @Field("code") code: String?,
        @Field("redirect_uri") redirect_uri: String?,
    ): TokenResponse

}