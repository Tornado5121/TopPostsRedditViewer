package com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts

import com.google.gson.annotations.SerializedName

data class TopRedditPosts(
    @SerializedName("data")
    val TopPostsData: TopPostsData
)