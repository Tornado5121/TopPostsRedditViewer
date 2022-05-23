package com.zhadko.topredditpostsviewer.data.network

import android.util.Log.d
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import com.zhadko.topredditpostsviewer.models.jsonModelsForTopPosts.asTopPostDomainModel

class TopPostsFetcherImpl(
    private val api: Requests
) : TopPostsFetcher {

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        val topPosts = api.getTopPosts()
        d("topPostsListToShow", topPosts.toString())
        return topPosts.data.asTopPostDomainModel()
    }

}