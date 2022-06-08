package com.zhadko.topredditpostsviewer.data.repositories.loadingRepository

import android.widget.ImageView
import kotlinx.coroutines.flow.StateFlow

interface LoadingRepository {

    val errorMessageFlow: StateFlow<String>

    fun saveImageToGallery(imageView: ImageView)

}