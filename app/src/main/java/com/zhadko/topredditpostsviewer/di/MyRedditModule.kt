package com.zhadko.topredditpostsviewer.di

import com.zhadko.topredditpostsviewer.data.database.PostsDatabase
import com.zhadko.topredditpostsviewer.data.database.PostsDatabaseRepository
import com.zhadko.topredditpostsviewer.data.network.RetrofitClient
import com.zhadko.topredditpostsviewer.data.network.TopPostsFetcherImpl
import com.zhadko.topredditpostsviewer.data.repositories.*
import com.zhadko.topredditpostsviewer.ui.detailedPostScreen.DetailedPostViewModel
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

val topPostsDataBaseRepositoryQualifier = StringQualifier("topPostsDataBaseRepoQualifier")
val topPostsRepositoryQualifier = StringQualifier("topPostsRepositoryQualifier")

val dataModule = module {
    single {
        TopPostsFetcherImpl(
            RetrofitClient.api
        )
    }.binds(
        arrayOf(
            TopPostsFetcher::class,
            TopPostsNewPageFetcher::class,
            SupportTopPostsData::class
        )
    )
    single<PostsRepository>(topPostsDataBaseRepositoryQualifier) {
        PostsDatabaseRepository(
            PostsDatabase.getInstance(androidContext()).topPostDao
        )
    }
    single<PostsRepository>(topPostsRepositoryQualifier) {
        TopPostsRepository(
            get(topPostsDataBaseRepositoryQualifier), get(), get(), get()
        )
    }
}

val viewModelModule = module {
    viewModel { TopPostsListViewModel(get(topPostsRepositoryQualifier)) }
    viewModel { (id: String) -> DetailedPostViewModel(id, get(topPostsRepositoryQualifier)) }
}