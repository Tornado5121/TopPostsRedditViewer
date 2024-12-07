package com.zhadko.topredditpostsviewer.domain.repositories

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

interface PostsRepository : TopPostsFetcher {

    suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>)
    suspend fun getTopPostById(id: String): TopPostDomainModel
    suspend fun clearAllTopPosts()

}