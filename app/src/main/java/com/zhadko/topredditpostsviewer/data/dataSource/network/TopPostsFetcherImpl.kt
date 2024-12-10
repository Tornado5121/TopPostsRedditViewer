package com.zhadko.topredditpostsviewer.data.dataSource.network

import com.zhadko.topredditpostsviewer.domain.mappers.asTopPostDomainModel
import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import com.zhadko.topredditpostsviewer.domain.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsNewPageFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class TopPostsFetcherImpl(
    private val api: Requests,
) : TopPostsFetcher, TopPostsNewPageFetcher, SupportTopPostsData {

    private val mPostIdAfterWhichPostsExistFlow = MutableStateFlow("")
    override val postIdAfterWhichPostsExistFlow = mPostIdAfterWhichPostsExistFlow

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        return withContext(Dispatchers.IO) {
            val topPosts = api.getTopPosts()
            val postIdAfterWhichPostsExistFlow = topPosts.TopPostsData.after
            mPostIdAfterWhichPostsExistFlow
                .emit(postIdAfterWhichPostsExistFlow)
            topPosts.TopPostsData.asTopPostDomainModel()
        }
    }

    override suspend fun getTopPostsNewPage(
        postIdAfter: String,
    ): List<TopPostDomainModel> {
        return withContext(Dispatchers.IO) {
            val newPageTopPosts = api.getTopPostsNewPage(postIdAfter)
            val postIdAfterWhichPostsExistFlow = newPageTopPosts.TopPostsData.after
            mPostIdAfterWhichPostsExistFlow
                .emit(postIdAfterWhichPostsExistFlow)
            newPageTopPosts.TopPostsData.asTopPostDomainModel()
        }
    }
}