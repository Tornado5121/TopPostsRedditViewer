package com.zhadko.topredditpostsviewer.di

import com.zhadko.topredditpostsviewer.data.dataSource.dataStore.UserDataStore
import com.zhadko.topredditpostsviewer.data.dataSource.dataStore.UserDataStoreImpl
import com.zhadko.topredditpostsviewer.data.dataSource.database.PostsDatabase
import com.zhadko.topredditpostsviewer.data.dataSource.database.PostsDatabaseRepository
import com.zhadko.topredditpostsviewer.data.dataSource.network.Requests
import com.zhadko.topredditpostsviewer.data.dataSource.network.TopPostsFetcherImpl
import com.zhadko.topredditpostsviewer.data.dataSource.network.interceptors.AuthInterceptor
import com.zhadko.topredditpostsviewer.data.repositories.AuthRepositoryImpl
import com.zhadko.topredditpostsviewer.data.repositories.LoadingRepositoryImpl
import com.zhadko.topredditpostsviewer.data.repositories.PermissionRepositoryImpl
import com.zhadko.topredditpostsviewer.data.repositories.TopPostsRepository
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import com.zhadko.topredditpostsviewer.domain.repositories.LoadingRepository
import com.zhadko.topredditpostsviewer.domain.repositories.PermissionRepository
import com.zhadko.topredditpostsviewer.domain.repositories.PostsRepository
import com.zhadko.topredditpostsviewer.domain.repositories.SupportTopPostsData
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsFetcher
import com.zhadko.topredditpostsviewer.domain.repositories.TopPostsNewPageFetcher
import com.zhadko.topredditpostsviewer.ui.authScreen.AuthViewModel
import com.zhadko.topredditpostsviewer.ui.detailedPostScreen.DetailedPostViewModel
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsListViewModel
import com.zhadko.topredditpostsviewer.utils.MyAuthRes.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val topPostsDataBaseRepositoryQualifier = StringQualifier("topPostsDataBaseRepoQualifier")
val topPostsRepositoryQualifier = StringQualifier("topPostsRepositoryQualifier")

val networkModule = module {

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(AuthInterceptor(get()))
            }.build())
            .baseUrl(BASE_URL)
            .build().create(Requests::class.java)
    }

    single {
        TopPostsFetcherImpl(
            get()
        )
    }.binds(
        arrayOf(
            TopPostsFetcher::class,
            TopPostsNewPageFetcher::class,
            SupportTopPostsData::class
        )
    )
}

val authModule = module {

    single<AuthRepository> { AuthRepositoryImpl(get(), get(), androidContext()) }
}

val dataModule = module {

    single<UserDataStore> { UserDataStoreImpl(androidContext()) }

    single<LoadingRepository> { LoadingRepositoryImpl() }

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