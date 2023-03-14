package com.example.newsreader.ui.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.data.repository.ArticleRepository
import com.example.newsreader.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val STOP_TIMEOUT_MILLIS = 5000L
private val topics = listOf("Apple", "Facebook", "Google")

data class UiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList()
)

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val uiState: StateFlow<UiState> by lazy {

        combine(_isLoading, articleRepository.getArticles(topics)) { isLoading, articles ->
            UiState(
                isLoading = isLoading,
                articles = articles
            )
        }
            .onStart { refreshArticles() }
            .stateIn(
                scope = viewModelScope,
                initialValue = UiState(isLoading = true),
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS)
            )
    }

    private fun refreshArticles() {
        _isLoading.value = true
        viewModelScope.launch {
            articleRepository.refreshArticles(topics)
            _isLoading.value = false
        }
    }
}