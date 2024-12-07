package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.domain.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsNewPageFetcher

class TopPostsRepository(
    private val postsRepository: PostsRepository,
    private val topPostsFetcher: TopPostsFetcher,
    private val topPostsNewPageFetcher: TopPostsNewPageFetcher,
    private val supportTopPostsData: SupportTopPostsData
) : PostsRepository {

    private var isCachedDataExistOrNeeded = false

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        return try {
            val idPostsAfterWhichPostsExist =
                supportTopPostsData.postIdAfterWhichPostsExistFlow.value
            val topPosts: List<TopPostDomainModel> = if (idPostsAfterWhichPostsExist.isEmpty()) {
                topPostsFetcher.getTopPostsPage()
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
                postsRepository.getTopPostsPage()
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