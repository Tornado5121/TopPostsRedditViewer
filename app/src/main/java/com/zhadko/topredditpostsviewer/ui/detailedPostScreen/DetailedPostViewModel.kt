package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailedPostViewModel(
    private val id: String,
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val mTopPostLiveData = MutableLiveData<TopPostDomainModel>()
    val topPostLiveData = mTopPostLiveData

    fun getTopPostById() {
        viewModelScope.launch(Dispatchers.IO) {
            val myTopPost = postsRepository.getTopPostById(id)
            mTopPostLiveData.postValue(myTopPost)
        }
    }

}