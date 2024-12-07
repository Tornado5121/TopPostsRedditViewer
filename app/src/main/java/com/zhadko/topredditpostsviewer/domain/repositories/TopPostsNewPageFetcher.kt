package com.zhadko.topredditpostsviewer.domain.repositories

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

interface TopPostsNewPageFetcher {

    suspend fun getTopPostsNewPage(postIdAfter: String): List<TopPostDomainModel>

}