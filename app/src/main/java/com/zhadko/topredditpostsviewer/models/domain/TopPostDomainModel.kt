package com.zhadko.topredditpostsviewer.models.domain

import com.zhadko.topredditpostsviewer.data.database.topPost.TopPostsEntity

data class TopPostDomainModel(
    val id: String,
    val author_fullname: String,
    val created: Double,
    val thumbnail_link: String,
    val comments_number: Int,
    val bigSizePictureUrl: String
)

fun TopPostDomainModel.asDatabaseModel(): TopPostsEntity {
    return TopPostsEntity(
        id = id,
        author_fullname = author_fullname,
        created = created,
        thumbnail = thumbnail_link,
        num_comments = comments_number,
        bigSizePictureUrl = bigSizePictureUrl
    )
}

fun List<TopPostDomainModel>.asDataBaseList(): List<TopPostsEntity> {
    return map {
        it.asDatabaseModel()
    }
}