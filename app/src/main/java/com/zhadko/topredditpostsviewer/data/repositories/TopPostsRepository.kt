package com.zhadko.topredditpostsviewer.data.repositories

class TopPostsRepository(
    private val postsRepository: PostsRepository,
    private val topPostsFetcher: TopPostsFetcher
) : PostsRepository {

}