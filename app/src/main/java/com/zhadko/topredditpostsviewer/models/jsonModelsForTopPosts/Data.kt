package com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts


import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

data class Data(
    val after: String,
    val children: List<Children>,
)

fun Data.asTopPostDomainModel(): List<TopPostDomainModel> {
    var bigSizePictureUrl: String
    return children.map {
        bigSizePictureUrl = if (it.data.is_reddit_media_domain) {
            it.data.preview.images[0].source.url
        } else {
            ""
        }
        TopPostDomainModel(
            id = it.data.id,
            author_fullname = it.data.author_fullname,
            created = it.data.created,
            thumbnail_link = it.data.thumbnail,
            comments_number = it.data.num_comments,
            bigSizePictureUrl = bigSizePictureUrl
        )
    }
}

