package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopPostsListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val mTopPostsLiveData = MutableLiveData<List<TopPostDomainModel>>()
    val topPostsLiveData = mTopPostsLiveData

    fun getTopPostsNewPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val newTopPostsPage = postsRepository.getTopPostsPage()
            val currentList = mTopPostsLiveData.value ?: listOf()
            mTopPostsLiveData.postValue(currentList + newTopPostsPage)
        }
    }

}