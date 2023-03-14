package com.example.newsreader.ui.newsdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsreader.domain.model.Article

@Composable
fun NewsDetails(article: Article, modifier: Modifier = Modifier) {
    Text(text = article.title)
    // TODO: Rest of the owl
}