package com.zhadko.topredditpostsviewer.data.database

import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

class PostsDatabaseRepository : PostsRepository {

    override suspend fun getTopPosts(): List<TopPostDomainModel> {
        //todo finish implementation data base
        return listOf()
    }

}