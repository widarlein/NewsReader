package com.example.newsreader.data.repository

import com.example.newsreader.data.datasource.NewsApiDataSource
import com.example.newsreader.data.datasource.db.ArticleDao
import com.example.newsreader.data.di.Io
import com.example.newsreader.data.toDomain
import com.example.newsreader.data.toLocal
import com.example.newsreader.domain.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ArticleRepository {
    fun getArticles(topics: List<String>): Flow<List<Article>>
    suspend fun refreshArticles(topics: List<String>)
    fun getArticle(url: String): Flow<Article>
}

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val dataSource: NewsApiDataSource,
    @Io private val dispatcher: CoroutineDispatcher
) : ArticleRepository {

    override fun getArticles(topics: List<String>): Flow<List<Article>> {
        return articleDao.observeArticles()
            .map { localArticles ->
                localArticles.map { localArticle -> localArticle.toDomain() }
            }
    }

    override suspend fun refreshArticles(topics: List<String>) {
        withContext(dispatcher) {
            val deferreds = topics.map { topic ->
                async {
                    dataSource.getNews(topic)
                }
            }
            val freshArticles = deferreds.awaitAll()
            freshArticles.filter { it.isSuccess }.forEach {
                it.onSuccess { reponse ->
                    reponse.articles.forEach { article ->
                        articleDao.insertArticle(article.toLocal())
                    }
                }
            }
        }
    }

    override fun getArticle(url: String): Flow<Article> {
        return articleDao.observeArticleByUrl(url)
            .map { it.toDomain() }
    }

}