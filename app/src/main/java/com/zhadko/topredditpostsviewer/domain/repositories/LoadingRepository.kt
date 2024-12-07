package com.zhadko.topredditpostsviewer.domain.repositories

import android.widget.ImageView
import kotlinx.coroutines.flow.StateFlow

interface LoadingRepository {

    val errorMessageFlow: StateFlow<String>

    fun saveImageToGallery(imageView: ImageView)

}