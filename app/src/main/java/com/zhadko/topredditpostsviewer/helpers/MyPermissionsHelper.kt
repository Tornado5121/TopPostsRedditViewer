package com.zhadko.topredditpostsviewer.helpers

import android.Manifest

object MyPermissionsHelper {

    val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

}