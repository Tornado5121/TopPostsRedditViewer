package com.zhadko.topredditpostsviewer.data.repositories.loadingRepository

import android.os.Environment
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URL

class LoadingRepositoryImpl : LoadingRepository {

    private val mErrorMessageFlow = MutableStateFlow("")
    override val errorMessageFlow = mErrorMessageFlow

    override fun saveImageToGallery(urlAddress: String) {
        try {
            val imageUrl = URL(urlAddress)
            val input = imageUrl.openStream()

            input.use {
                val storagePath = Environment.getExternalStorageDirectory()
                val output: OutputStream = FileOutputStream("$storagePath/myImage.png")
                output.use {
                    val buffer = ByteArray(55000)
                    var bytesRead: Int
                    while (input.read(buffer, 0, buffer.size).also { bytesRead = it } >= 0) {
                        output.write(buffer, 0, bytesRead)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mErrorMessageFlow.value = "Error"
        }
    }

}

