package com.zhadko.topredditpostsviewer.di

import com.zhadko.topredditpostsviewer.data.database.PostsDatabase
import com.zhadko.topredditpostsviewer.data.database.PostsDatabaseRepository
import com.zhadko.topredditpostsviewer.data.network.RetrofitClient
import com.zhadko.topredditpostsviewer.data.network.TopPostsFetcherImpl
import com.zhadko.topredditpostsviewer.data.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsRepository
import com.zhadko.topredditpostsviewer.ui.detailedPostScreen.DetailedPostViewModel
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val topPostsDataBaseRepositoryQualifier = StringQualifier("topPostsDataBaseRepoQualifier")
val topPostsRepositoryQualifier = StringQualifier("topPostsRepositoryQualifier")

val dataModule = module {
    factory<TopPostsFetcher> {
        TopPostsFetcherImpl(
            RetrofitClient.api
        )
    }
    factory<PostsRepository>(topPostsDataBaseRepositoryQualifier) {
        PostsDatabaseRepository(
            PostsDatabase.getInstance(androidContext()).topPostsDao
        )
    }
    single<PostsRepository>(topPostsRepositoryQualifier) {
        TopPostsRepository(
            get(
                topPostsDataBaseRepositoryQualifier
            ), get()
        )
    }
}

val viewModelModule = module {
    viewModel { TopPostsListViewModel(get(topPostsRepositoryQualifier)) }
    viewModel { DetailedPostViewModel(get()) }
}