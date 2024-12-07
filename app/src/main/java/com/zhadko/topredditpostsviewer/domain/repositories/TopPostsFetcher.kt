package com.zhadko.topredditpostsviewer.domain.repositories

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

interface TopPostsFetcher {

    suspend fun getTopPostsPage(): List<TopPostDomainModel>

}