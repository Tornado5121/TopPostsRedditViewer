package com.zhadko.topredditpostsviewer.data.repositories

import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

interface TopPostsNewPageFetcher {

    suspend fun getTopPostsNewPage(postIdAfter: String): List<TopPostDomainModel>

}