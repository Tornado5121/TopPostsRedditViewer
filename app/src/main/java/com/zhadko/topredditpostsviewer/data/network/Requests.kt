package com.zhadko.topredditpostsviewer.data.network

import com.zhadko.topredditpostsviewer.data.network.dto.auth.TokenResponse
import com.zhadko.topredditpostsviewer.data.network.dto.TopRedditPosts
import retrofit2.http.*

interface Requests {

    @GET("top.json")
    suspend fun getTopPostsNewPage(
        @Header("X-Modhash") XModhash: String?,
        @Query("after") postIdAfter: String
    ): TopRedditPosts

    @GET("top.json")
    suspend fun getTopPosts(
        @Header("X-Modhash") XModhash: String?
    ): TopRedditPosts

    @POST("api/v1/access_token")
    @FormUrlEncoded
    fun getToken(
        @Header("Authorization") Authorization: String?,
        @Field("grant_type") grant_type: String?,
        @Field("code") code: String?,
        @Field("redirect_uri") redirect_uri: String?
    ): TokenResponse

}