package com.example.newsreader.ui.newslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsreader.domain.model.Article

@Composable
fun NewsList(
    articles: List<Article>,
    onNavigateToNewsDetails: () -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        modifier = modifier
    ) {
        itemsIndexed(articles) { index, article ->
            NewsRow(article = article, modifier = Modifier.clickable { onNavigateToNewsDetails() })
            if (index != articles.lastIndex) {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}