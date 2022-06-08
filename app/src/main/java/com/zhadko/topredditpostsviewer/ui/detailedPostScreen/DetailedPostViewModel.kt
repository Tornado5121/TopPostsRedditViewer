package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.data.repositories.loadingRepository.LoadingRepository
import com.zhadko.topredditpostsviewer.data.repositories.permissionRepository.PermissionRepository
import com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailedPostViewModel(
    private val id: String,
    private val postsRepository: PostsRepository,
    private val loadingRepository: LoadingRepository,
    private val permissionRepository: PermissionRepository
) : ViewModel() {

    private val mTopPostLiveData = MutableLiveData<TopPostDomainModel>()
    val topPostLiveData: LiveData<TopPostDomainModel> = mTopPostLiveData

    private val mErrorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData = mErrorMessageLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingRepository.errorMessageFlow.collectLatest {
                mErrorMessageLiveData.postValue(it)
            }
        }
    }

    fun getTopPostById() {
        viewModelScope.launch(Dispatchers.IO) {
            val myTopPost = postsRepository.getTopPostById(id)
            mTopPostLiveData.postValue(myTopPost)
        }
    }

    fun saveImageToGallery(imageView: ImageView) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingRepository.saveImageToGallery(imageView)
        }
    }

    fun isStoragePermissionGranted() = permissionRepository.isStoragePermissionsGranted()

}