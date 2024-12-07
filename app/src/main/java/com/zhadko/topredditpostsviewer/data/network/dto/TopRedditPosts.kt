package com.zhadko.topredditpostsviewer.data.network.dto

import com.google.gson.annotations.SerializedName

data class TopRedditPosts(
    @SerializedName("data")
    val TopPostsData: TopPostsData
)