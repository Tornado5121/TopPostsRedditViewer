package com.zhadko.topredditpostsviewer.data.dataSource.network.dto

import com.google.gson.annotations.SerializedName

data class TopPostObject(
    @SerializedName("data")
    val topPostData: TopPostDataObject,
)