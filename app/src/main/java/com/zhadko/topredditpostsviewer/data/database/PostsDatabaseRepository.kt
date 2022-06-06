package com.zhadko.topredditpostsviewer.data.database

import com.zhadko.topredditpostsviewer.data.database.topPost.TopPostDao
import com.zhadko.topredditpostsviewer.data.database.topPost.asDomainModel
import com.zhadko.topredditpostsviewer.data.database.topPost.asDomainModelList
import com.zhadko.topredditpostsviewer.data.repositories.topPostsRepository.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel
import com.zhadko.topredditpostsviewer.models.domain.asDataBaseList

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