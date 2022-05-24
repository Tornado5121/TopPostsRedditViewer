package com.zhadko.topredditpostsviewer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopPostsDao {

    @Insert
    fun addTopPosts(topPosts: List<TopPostsEntity>)

    @Query("SELECT * from TopPostsEntity")
    fun getTopPostsList(): List<TopPostsEntity>

    @Query("SELECT * from TopPostsEntity WHERE id = :id")
    fun getTopPostById(id: String): TopPostsEntity

    @Query("DELETE from TopPostsEntity")
    fun clearDatabase()

}