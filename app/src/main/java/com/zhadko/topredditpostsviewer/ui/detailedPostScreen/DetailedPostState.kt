package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel

data class DetailedPostState(
    val isLoading: Boolean,
    val postModel: TopPostDomainModel?,
)
