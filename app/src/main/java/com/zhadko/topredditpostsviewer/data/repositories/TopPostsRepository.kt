package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

class TopPostsRepository(
    private val postsRepository: PostsRepository,
    private val topPostsFetcher: TopPostsFetcher,
    private val topPostsNewPageFetcher: TopPostsNewPageFetcher,
    private val supportTopPostsData: SupportTopPostsData
) : PostsRepository {

    private var isCachedDataExistOrNeeded = false

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        return try {
            val idPostsAfterWhichPostsExist =
                supportTopPostsData.postIdAfterWhichPostsExistFlow.value
            val topPosts: List<TopPostDomainModel> = if (idPostsAfterWhichPostsExist.isEmpty()) {
                topPostsFetcher.getTopPosts()
            } else {
                topPostsNewPageFetcher.getTopPostsNewPage(idPostsAfterWhichPostsExist)
            }

            if (!isCachedDataExistOrNeeded) {
                postsRepository.clearAllTopPosts()
                isCachedDataExistOrNeeded = true
            }

            insertTopPosts(topPosts)
            topPosts
        } catch (e: Exception) {
            e.printStackTrace()
            if (!isCachedDataExistOrNeeded) {
                isCachedDataExistOrNeeded = true
                postsRepository.getTopPosts()
            } else {
                emptyList()
            }

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