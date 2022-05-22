package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import androidx.lifecycle.ViewModel
import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository

class DetailedPostViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {
}