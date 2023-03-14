package com.example.newsreader.data.di

import android.content.Context
import androidx.room.Room
import com.example.newsreader.BuildConfig
import com.example.newsreader.data.datasource.NewsApiDataSource
import com.example.newsreader.data.datasource.NewsApiDataSourceImpl
import com.example.newsreader.data.datasource.db.ArticleDao
import com.example.newsreader.data.datasource.db.ArticleDatabase
import com.example.newsreader.data.datasource.service.NewsApiService
import com.example.newsreader.data.repository.ArticleRepository
import com.example.newsreader.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

const val API_BASE_URL = "https://newsapi.org/v2/"

@Module(includes = [Bindings::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(API_BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "Articles.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDatabase): ArticleDao = database.articleDao()

    @ApiKey
    @Provides
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Io
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Main
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}

@Module
@InstallIn(SingletonComponent::class)
interface Bindings {

    @Binds
    fun bindNewsApiDataSource(impl: NewsApiDataSourceImpl): NewsApiDataSource

    @Binds
    fun bindArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Io

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Main