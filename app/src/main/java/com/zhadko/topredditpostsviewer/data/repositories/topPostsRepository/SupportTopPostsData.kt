package com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository

import kotlinx.coroutines.flow.MutableStateFlow

interface SupportTopPostsData {

    val postIdAfterWhichPostsExistFlow: MutableStateFlow<String>

}