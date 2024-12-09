package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.base.BaseViewModel
import com.zhadko.topredditpostsviewer.domain.repositories.LoadingRepository
import com.zhadko.topredditpostsviewer.domain.repositories.PermissionRepository
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailedPostViewModel(
    private val id: String,
    private val postsRepository: PostsRepository,
    private val loadingRepository: LoadingRepository,
    private val permissionRepository: PermissionRepository,
) : BaseViewModel<DetailedPostState>(
    DetailedPostState(
        isLoading = true,
        postModel = null
    )
) {

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
            updateState(state.value.copy(isLoading = false, postModel = myTopPost))
        }
    }

    fun saveImageToGallery(imageView: ImageView) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingRepository.saveImageToGallery(imageView)
        }
    }

    fun isStoragePermissionGranted() = permissionRepository.isStoragePermissionsGranted()

}