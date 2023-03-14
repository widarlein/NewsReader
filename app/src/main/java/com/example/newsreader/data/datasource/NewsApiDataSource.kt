package com.example.newsreader.data.datasource

import com.example.newsreader.data.datasource.service.NewsApiService
import com.example.newsreader.data.di.ApiKey
import com.example.newsreader.data.model.exceptions.UnsuccessfulRequestException
import com.example.newsreader.data.model.remote.NewsResponse
import java.io.IOException
import javax.inject.Inject

interface NewsApiDataSource {
    suspend fun getNews(topic: String): Result<NewsResponse>
}

class NewsApiDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    @ApiKey private val apiKey: String
) : NewsApiDataSource {
    override suspend fun getNews(topic: String): Result<NewsResponse> {
        return try {

            val response = newsApiService.getNews(
                topic = topic,
                apiKey = apiKey
            )
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                Result.failure(UnsuccessfulRequestException(response.message(), requestSubject = topic))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException, is RuntimeException -> Result.failure(e)
                else -> throw (e)
            }

        }
    }


}