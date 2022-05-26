package com.zhadko.topredditpostsviewer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhadko.topredditpostsviewer.data.database.topPost.TopPostDao
import com.zhadko.topredditpostsviewer.data.database.topPost.TopPostsEntity

@Database(
    entities = [TopPostsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {

    abstract val topPostDao: TopPostDao

    companion object {

        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getInstance(context: Context): PostsDatabase {
            val databaseInstance = INSTANCE
            return if (databaseInstance != null) {
                databaseInstance
            } else {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDatabase::class.java,
                    "posts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
















