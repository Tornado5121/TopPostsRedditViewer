package com.zhadko.topredditpostsviewer.data.repositories.loadingRepository

import kotlinx.coroutines.flow.MutableStateFlow

interface LoadingRepository {

    val errorMessageFlow: MutableStateFlow<String>

    fun saveImageToGallery(urlAddress: String)

}