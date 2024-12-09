package com.zhadko.topredditpostsviewer.data.dataSource.database

import com.zhadko.topredditpostsviewer.domain.models.TopPostDomainModel
import com.zhadko.topredditpostsviewer.domain.models.asDataBaseList
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsDatabaseRepository(
    private val topPostDao: TopPostDao,
) : PostsRepository {

    override suspend fun getTopPostsPage(): List<TopPostDomainModel> {
        return withContext(Dispatchers.IO) { topPostDao.getTopPostsList().asDomainModelList() }
    }

    override suspend fun insertTopPosts(topPosts: List<TopPostDomainModel>) {
        withContext(Dispatchers.IO) { topPostDao.addTopPosts(topPosts.asDataBaseList()) }
    }

    override suspend fun getTopPostById(id: String): TopPostDomainModel {
        return topPostDao.getTopPostById(id).asDomainModel()
    }

    override suspend fun clearAllTopPosts() {
        withContext(Dispatchers.IO) { topPostDao.clearDatabase() }
    }

}