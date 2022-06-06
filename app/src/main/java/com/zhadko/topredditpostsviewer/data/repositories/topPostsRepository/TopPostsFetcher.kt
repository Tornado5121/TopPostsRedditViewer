package com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

interface TopPostsFetcher {

    suspend fun getTopPostsPage(): List<TopPostDomainModel>

}