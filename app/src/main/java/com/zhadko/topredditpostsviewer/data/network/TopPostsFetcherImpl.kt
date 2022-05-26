package com.zhadko.topredditpostsviewer.data.network

import android.util.Log.d
import com.zhadko.topredditpostsviewer.data.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsNewPageFetcher
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts.asTopPostDomainModel
import kotlinx.coroutines.flow.MutableStateFlow

class TopPostsFetcherImpl(
    private val api: Requests
) : TopPostsFetcher, TopPostsNewPageFetcher, SupportTopPostsData {

    private val mPostIdAfterWhichPostsExistFlow = MutableStateFlow("")
    override val postIdAfterWhichPostsExistFlow = mPostIdAfterWhichPostsExistFlow

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        val topPosts = api.getTopPosts()
        val postIdAfterWhichPostsExistFlow = topPosts.data.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return topPosts.data.asTopPostDomainModel()
    }

    override suspend fun getTopPostsNewPage(
        postIdAfter: String
    ): List<TopPostDomainModel> {
        val newPageTopPosts = api.getTopPostsNewPage(postIdAfter)
        val postIdAfterWhichPostsExistFlow = newPageTopPosts.data.after
        mPostIdAfterWhichPostsExistFlow
            .emit(postIdAfterWhichPostsExistFlow)
        return newPageTopPosts.data.asTopPostDomainModel()
    }

}