package com.example.newsreader.ui.newslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsreader.domain.model.Article

@Composable
fun NewsRow(article: Article, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column {
            Row {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(text = article.description, modifier = Modifier.padding(top = 16.dp))
        }
    }

}

@Preview
@Composable
fun NewsRowPreview() {
    NewsRow(
        Article(
            title = "Titel",
            description = "A longer description",
            author = "A D Hadori",
            content = "A content",
            publishedAt = "date",
            url = "url",
            urlToImage = "https://c.biztoc.com/p/7579a7a8bb017ec0/og.webp"
        )
    )
}