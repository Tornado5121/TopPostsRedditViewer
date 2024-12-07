package com.zhadko.topredditpostsviewer.domain.repositories

interface PermissionRepository {

    fun isStoragePermissionsGranted() : Boolean

}