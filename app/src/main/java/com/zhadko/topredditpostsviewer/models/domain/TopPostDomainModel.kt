package com.zhadko.topredditpostsviewer.models.domain

data class TopPostDomainModel(
    val id: String,
    val author_fullname: String,
    val created: Double,
    val thumbnail: String,
    val num_comments: Int
)
