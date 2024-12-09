package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.base.BaseViewModel
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import kotlinx.coroutines.launch

class TopPostsListViewModel(
    private val postsRepository: PostsRepository,
) : BaseViewModel<TopPostsState>(
    TopPostsState(
        isLoading = true,
        topPostsList = emptyList()
    )
) {

    init {
        getTopPostsNewPage()
    }

    fun getTopPostsNewPage() {
        viewModelScope.launch {
            val newTopPostsPage = postsRepository.getTopPostsPage()
            val currentList = state.value
            updateState(state.value.copy(isLoading = false, topPostsList = currentList.topPostsList + newTopPostsPage))
        }
    }
}