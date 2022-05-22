package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import androidx.lifecycle.ViewModel
import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository

class TopPostsViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {
}