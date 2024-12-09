package com.zhadko.topredditpostsviewer.data.dataSource.database

import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import com.zhadko.topredditpostsviewer.domain.models.asDataBaseList

class PostsDatabaseRepository(
    private val topPostDao: TopPostDao
) : PostsRepository {

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        return topPostDao.getTopPostsList().asDomainModelList()
    }

    override suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>) {
        topPostDao.addTopPosts(topPosts.asDataBaseList())
    }

    override suspend fun getTopPostById(id: String): TopPostDomainModel {
        return topPostDao.getTopPostById(id).asDomainModel()
    }

    override suspend fun clearAllTopPosts() {
        topPostDao.clearDatabase()
    }

}