package com.zhadko.topredditpostsviewer.di

import com.zhadko.topredditpostsviewer.auth.Auth
import com.zhadko.topredditpostsviewer.data.database.PostsDatabase
import com.zhadko.topredditpostsviewer.data.database.PostsDatabaseRepository
import com.zhadko.topredditpostsviewer.data.network.RetrofitClient
import com.zhadko.topredditpostsviewer.data.network.TopPostsFetcherImpl
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsRepository
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import com.zhadko.topredditpostsviewer.data.repositories.AuthRepositoryImpl
import com.zhadko.topredditpostsviewer.domain.repositories.LoadingRepository
import com.zhadko.topredditpostsviewer.data.repositories.LoadingRepositoryImpl
import com.zhadko.topredditpostsviewer.domain.repositories.PermissionRepository
import com.zhadko.topredditpostsviewer.data.repositories.PermissionRepositoryImpl
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.domain.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsNewPageFetcher
import com.zhadko.topredditpostsviewer.ui.authScreen.AuthViewModel
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

    single<AuthRepository> { AuthRepositoryImpl(Auth(androidContext(), RetrofitClient.api)) }

    single<LoadingRepository> { LoadingRepositoryImpl() }

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

    single<PermissionRepository> { PermissionRepositoryImpl(androidContext()) }

}

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TopPostsListViewModel(get(topPostsRepositoryQualifier)) }
    viewModel { (id: String) ->
        DetailedPostViewModel(
            id,
            get(topPostsRepositoryQualifier),
            get(), get()
        )
    }
}