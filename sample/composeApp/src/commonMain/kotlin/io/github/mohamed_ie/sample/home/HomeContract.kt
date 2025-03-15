package io.github.mohamed_ie.sample.home

import io.github.mohamed_ie.uistate.annotations.UiState

sealed interface HomeUiState {
    data object Loading : HomeUiState

    @UiState
    data class Success(val result: String) : HomeUiState
}