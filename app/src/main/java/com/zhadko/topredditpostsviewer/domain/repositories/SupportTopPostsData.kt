package com.zhadko.topredditpostsviewer.domain.repositories

import kotlinx.coroutines.flow.MutableStateFlow

interface SupportTopPostsData {

    val postIdAfterWhichPostsExistFlow: MutableStateFlow<String>

}