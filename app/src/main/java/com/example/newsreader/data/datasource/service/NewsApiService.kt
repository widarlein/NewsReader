package com.example.newsreader.data.datasource.service

import com.example.newsreader.data.model.remote.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything?sortBy=publishedAt")
    suspend fun getNews(@Query("q") topic: String, @Query("apiKey") apiKey: String): Response<NewsResponse>
}