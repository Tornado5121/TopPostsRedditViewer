package com.zhadko.topredditpostsviewer.di

import com.zhadko.topredditpostsviewer.data.database.PostsDatabaseRepository
import com.zhadko.topredditpostsviewer.data.network.TopPostsFetcherImpl
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsRepository
import com.zhadko.topredditpostsviewer.ui.detailedPostScreen.DetailedPostViewModel
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { TopPostsRepository(get(), get()) }
    single { TopPostsFetcherImpl() }
    single { PostsDatabaseRepository() }
}

val viewModelModule = module {
    viewModel { TopPostsViewModel(get()) }
    viewModel { DetailedPostViewModel(get()) }
}