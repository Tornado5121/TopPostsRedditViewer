package com.zhadko.topredditpostsviewer.data.repositories.permissionRepository

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.zhadko.topredditpostsviewer.helpers.MyAppHelper

class PermissionRepositoryImpl(
    private val context: Context
) : PermissionRepository {

    override fun isStoragePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, MyAppHelper.REQUIRED_PERMISSIONS.toString()
        ) == PackageManager.PERMISSION_GRANTED
    }

}