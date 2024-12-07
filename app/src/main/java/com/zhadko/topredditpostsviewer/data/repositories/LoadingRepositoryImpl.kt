package com.zhadko.topredditpostsviewer.data.repositories

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.widget.ImageView
import com.zhadko.topredditpostsviewer.domain.repositories.LoadingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class LoadingRepositoryImpl : LoadingRepository {

    private val mErrorMessageFlow = MutableStateFlow("")
    override val errorMessageFlow: StateFlow<String> = mErrorMessageFlow

    override fun saveImageToGallery(imageView: ImageView) {
        try {
            val bitmapDrawable = imageView.drawable as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap

            val storagePath = Environment.getExternalStorageDirectory()
            val dir = File(storagePath.absolutePath + "/MyPictures")
            dir.mkdirs()

            val fileName = String.format("%d.png", System.currentTimeMillis())
            val outFile = File(dir, fileName)

            val output: OutputStream = FileOutputStream(outFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)

            output.flush()
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
            mErrorMessageFlow.value = "Error"
        }
    }

}

