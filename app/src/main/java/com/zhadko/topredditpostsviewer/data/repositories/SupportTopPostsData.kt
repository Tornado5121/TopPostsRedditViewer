package com.zhadko.topredditpostsviewer.data.repositories

import kotlinx.coroutines.flow.MutableStateFlow

interface SupportTopPostsData {

    val postIdAfterWhichPostsExistFlow: MutableStateFlow<String>

}