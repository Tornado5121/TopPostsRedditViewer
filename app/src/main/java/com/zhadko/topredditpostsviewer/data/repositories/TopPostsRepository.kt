package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

class TopPostsRepository(
    private val postsRepository: PostsRepository,
    private val topPostsFetcher: TopPostsFetcher
) : PostsRepository {

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        return try {
            val topPosts = topPostsFetcher.getTopPosts()
            insertTopPosts(topPosts)
            topPosts
        } catch (e: Exception) {
            e.printStackTrace()
            postsRepository.getTopPosts()
        }
    }

    override suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>) {
        postsRepository.insertTopPosts(topPosts)
    }

    override suspend fun getTopPostById(id: String): TopPostDomainModel {
        return postsRepository.getTopPostById(id)
    }

    override suspend fun clearAllTopPosts() {
        postsRepository.clearAllTopPosts()
    }

}