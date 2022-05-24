package com.zhadko.topredditpostsviewer.data.database

import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import com.zhadko.topredditpostsviewer.models.domain.asDataBaseList

class PostsDatabaseRepository(
    private val topPostsDao: TopPostsDao
) : PostsRepository {

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        return topPostsDao.getTopPostsList().asDomainModelList()
    }

    override suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>) {
        topPostsDao.addTopPosts(topPosts.asDataBaseList())
    }

    override suspend fun getTopPostById(id: String): TopPostDomainModel {
        return topPostsDao.getTopPostById(id).asDomainModel()
    }

    override suspend fun clearAllTopPosts() {
        topPostsDao.clearDatabase()
    }

}