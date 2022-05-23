package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopPostsListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val mTopPostsLiveData = MutableLiveData<List<TopPostDomainModel>>()
    val topPostsLiveData = mTopPostsLiveData

    fun getTopPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val topPosts = postsRepository.getTopPosts()
            mTopPostsLiveData.postValue(topPosts)
        }
    }

}