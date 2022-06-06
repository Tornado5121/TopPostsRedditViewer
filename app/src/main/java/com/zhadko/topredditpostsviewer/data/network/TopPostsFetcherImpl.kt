package com.zhadko.topredditpostsviewer.data.network

import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository.SupportTopPostsData
import com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository.TopPostsFetcher
import com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository.TopPostsNewPageFetcher
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts.asTopPostDomainModel
import kotlinx.coroutines.flow.MutableStateFlow

class TopPostsFetcherImpl(
    private val api: Requests
) : TopPostsFetcher, TopPostsNewPageFetcher, SupportTopPostsData {

    private val mPostIdAfterWhichPostsExistFlow = MutableStateFlow("")
    override val postIdAfterWhichPostsExistFlow = mPostIdAfterWhichPostsExistFlow

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        val topPosts = api.getTopPosts(Auth.access_token)
        val postIdAfterWhichPostsExistFlow = topPosts.data.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return topPosts.data.asTopPostDomainModel()
    }

    override suspend fun getTopPostsNewPage(
        postIdAfter: String
    ): List<TopPostDomainModel> {
        val newPageTopPosts = api.getTopPostsNewPage(Auth.access_token, postIdAfter)
        val postIdAfterWhichPostsExistFlow = newPageTopPosts.data.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return newPageTopPosts.data.asTopPostDomainModel()
    }

}