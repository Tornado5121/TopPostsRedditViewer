package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

interface PostsRepository : TopPostsFetcher {

    suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>)
    suspend fun getTopPostById(id: String): TopPostDomainModel
    suspend fun clearAllTopPosts()

}