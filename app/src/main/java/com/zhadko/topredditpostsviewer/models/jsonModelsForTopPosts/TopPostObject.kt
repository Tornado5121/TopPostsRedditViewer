package com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts

import com.google.gson.annotations.SerializedName

data class TopPostObject(
    @SerializedName("data")
    val topPostData: TopPostDataObject,
)