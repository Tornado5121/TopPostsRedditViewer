package com.zhadko.topredditpostsviewer.data.dataSource.network.dto

data class TopPostDataObject(
    val author_fullname: String,
    val created: Double,
    val created_utc: Double,
    val thumbnail: String,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val num_comments: Int,
    val preview: Preview,
    val id: String,
    val is_reddit_media_domain: Boolean
)