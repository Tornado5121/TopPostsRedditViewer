package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

class TopPostsRepository(
    private val topPostsFetcher: TopPostsFetcher
) : PostsRepository {

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        return try {
            topPostsFetcher.getTopPosts()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


}