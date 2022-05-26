package com.zhadko.topredditpostsviewer.data.database.topPost

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

@Entity
data class TopPostsEntity(
    @PrimaryKey
    val id: String,
    val author_fullname: String,
    val created: Double,
    val thumbnail: String,
    val num_comments: Int,
    val bigSizePictureUrl: String
)

fun TopPostsEntity.asDomainModel(): TopPostDomainModel {
    return TopPostDomainModel(
        id = id,
        author_fullname = author_fullname,
        created = created,
        thumbnail_link = thumbnail,
        comments_number = num_comments,
        bigSizePictureUrl = bigSizePictureUrl
    )
}

fun List<TopPostsEntity>.asDomainModelList(): List<TopPostDomainModel> {
    return map {
        it.asDomainModel()
    }
}