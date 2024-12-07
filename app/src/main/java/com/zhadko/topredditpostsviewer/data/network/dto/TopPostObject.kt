package com.zhadko.topredditpostsviewer.data.network.dto

import com.google.gson.annotations.SerializedName

data class TopPostObject(
    @SerializedName("data")
    val topPostData: TopPostDataObject,
)