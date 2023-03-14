package com.example.newsreader.data

import com.example.newsreader.data.datasource.db.LocalArticle
import com.example.newsreader.data.model.remote.Article
import com.example.newsreader.domain.model.Article as DomainArticle

fun Article.toLocal() = LocalArticle(
    url = this.url,
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    title = this.title,
    urlToImage = this.urlToImage,
)

fun LocalArticle.toDomain() = DomainArticle(
    url = this.url,
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    title = this.title,
    urlToImage = this.urlToImage,
)