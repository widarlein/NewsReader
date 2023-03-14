package com.example.newsreader.ui.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsreader.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel(),
    onNavigateToNewsDetails: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.news_list_title)) }) }
    ) { padding ->
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            NewsList(
                articles = uiState.articles,
                onNavigateToNewsDetails = onNavigateToNewsDetails,
                modifier = Modifier.padding(top = padding.calculateTopPadding()).background(color = MaterialTheme.colorScheme.background)
            )
        }

    }
}