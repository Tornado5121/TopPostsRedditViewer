package com.zhadko.topredditpostsviewer.domain.mappers

import com.zhadko.topredditpostsviewer.data.dataSource.database.TopPostsEntity
import com.zhadko.topredditpostsviewer.data.dataSource.network.dto.TopPostsData
import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

fun TopPostsData.asTopPostDomainModel(): List<TopPostDomainModel> {
    var bigSizePictureUrl: String
    return children.map {
        bigSizePictureUrl = if (it.topPostData.is_reddit_media_domain) {
            it.topPostData.preview.images[0].source.url
        } else {
            ""
        }
        TopPostDomainModel(
            id = it.topPostData.id,
            author_fullname = it.topPostData.author_fullname,
            created = it.topPostData.created,
            thumbnail_link = it.topPostData.thumbnail,
            comments_number = it.topPostData.num_comments,
            bigSizePictureUrl = bigSizePictureUrl
        )
    }
}

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

