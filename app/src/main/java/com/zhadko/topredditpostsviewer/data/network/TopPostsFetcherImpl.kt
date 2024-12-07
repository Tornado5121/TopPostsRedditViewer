package com.zhadko.topredditpostsviewer.data.network

import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.domain.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsNewPageFetcher
import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import com.zhadko.topredditpostsviewer.data.network.dto.asTopPostDomainModel
import kotlinx.coroutines.flow.MutableStateFlow

class TopPostsFetcherImpl(
    private val api: Requests
) : TopPostsFetcher, TopPostsNewPageFetcher, SupportTopPostsData {

    private val mPostIdAfterWhichPostsExistFlow = MutableStateFlow("")
    override val postIdAfterWhichPostsExistFlow = mPostIdAfterWhichPostsExistFlow

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        val topPosts = api.getTopPosts(Auth.access_token)
        val postIdAfterWhichPostsExistFlow = topPosts.TopPostsData.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return topPosts.TopPostsData.asTopPostDomainModel()
    }

    override suspend fun getTopPostsNewPage(
        postIdAfter: String
    ): List<TopPostDomainModel> {
        val newPageTopPosts = api.getTopPostsNewPage(Auth.access_token, postIdAfter)
        val postIdAfterWhichPostsExistFlow = newPageTopPosts.TopPostsData.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return newPageTopPosts.TopPostsData.asTopPostDomainModel()
    }

}