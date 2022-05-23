package com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts

data class DataX(
    val author_fullname: String,
    val created: Double,
    val created_utc: Double,
    val thumbnail: String,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val num_comments: Int,
    val preview: PreviewX,
    val id: String
)