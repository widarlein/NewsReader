package com.example.newsreader.ui.newsdetails

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
fun NewsDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.news_list_title)) }) }
    ) { padding ->
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            NewsDetails(
                article = uiState.article!!,
                modifier = Modifier.padding(top = padding.calculateTopPadding())
            )
        }

    }
}