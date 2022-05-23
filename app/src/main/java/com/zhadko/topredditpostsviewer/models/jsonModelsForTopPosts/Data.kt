package com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

data class Data(
    val children: List<Children>,
)

fun Data.asTopPostDomainModel(): List<TopPostDomainModel> {
    return children.map {
        TopPostDomainModel(
            id = it.data.id,
            author_fullname = it.data.author_fullname,
            created = it.data.created,
            thumbnail = it.data.thumbnail,
            num_comments = it.data.num_comments
        )
    }
}

