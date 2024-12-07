package com.zhadko.topredditpostsviewer.data.network.dto


import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

data class TopPostsData(
    val after: String,

    val children: List<TopPostObject>,
)

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

