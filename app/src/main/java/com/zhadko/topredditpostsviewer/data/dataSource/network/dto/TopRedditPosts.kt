package com.zhadko.topredditpostsviewer.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class TopRedditPosts(
    @SerializedName("data")
    val TopPostsData: TopPostsData
)