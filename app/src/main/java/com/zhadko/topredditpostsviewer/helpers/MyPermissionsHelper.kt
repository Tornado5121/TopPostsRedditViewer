package com.zhadko.topredditpostsviewer.helpers

import android.Manifest

object MyPermissionsHelper {

    const val REQUEST_CODE_PERMISSIONS = 10

    val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

}