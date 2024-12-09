package com.zhadko.topredditpostsviewer.data.dataSource.network.dto

data class TopPostsData(
    val after: String,

    val children: List<TopPostObject>,
)
