package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

data class TopPostsState(
    val isLoading: Boolean,
    val topPostsList: List<TopPostDomainModel>,
)
