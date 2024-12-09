package com.zhadko.topredditpostsviewer.data.repositories

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.zhadko.topredditpostsviewer.domain.repositories.PermissionRepository
import com.zhadko.topredditpostsviewer.utils.MyPermissionsHelper

class PermissionRepositoryImpl(
    private val context: Context
) : PermissionRepository {

    override fun isStoragePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, MyPermissionsHelper.REQUIRED_PERMISSIONS.toString()
        ) == PackageManager.PERMISSION_GRANTED
    }

}