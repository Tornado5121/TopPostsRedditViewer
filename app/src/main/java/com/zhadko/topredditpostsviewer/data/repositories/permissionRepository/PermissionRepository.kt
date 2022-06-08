package com.zhadko.topredditpostsviewer.data.repositories.permissionRepository

interface PermissionRepository {

    fun isStoragePermissionsGranted() : Boolean

}