package com.example.newsreader.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalArticle::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}