package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

interface TopPostsFetcher {

    suspend fun getTopPosts() : List<TopPostDomainModel>

}