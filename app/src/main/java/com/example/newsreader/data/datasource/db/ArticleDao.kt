package com.example.newsreader.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun observeArticles(): Flow<List<LocalArticle>>

    @Query("SELECT * FROM articles WHERE url = :articleUrl")
    fun observeArticleByUrl(articleUrl: String): Flow<LocalArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: LocalArticle)
}