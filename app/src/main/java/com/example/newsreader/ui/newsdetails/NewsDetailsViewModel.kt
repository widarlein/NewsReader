package com.example.newsreader.ui.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.data.repository.ArticleRepository
import com.example.newsreader.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

const val STOP_TIMEOUT_MILLIS = 5000L

data class UiState(
    val isLoading: Boolean = false,
    val article: Article? = null
)

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val uiState: StateFlow<UiState> by lazy {
        combine(_isLoading, articleRepository.getArticle(TODO())) { isLoading, article ->
            UiState(
                isLoading = isLoading,
                article = article
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = UiState(isLoading = true),
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS)
        )
    }

}