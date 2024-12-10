package com.zhadko.topredditpostsviewer.domain.models

data class TopPostDomainModel(
    val id: String,
    val author_fullname: String,
    val created: Double,
    val thumbnail_link: String,
    val comments_number: Int,
    val bigSizePictureUrl: String,
)
