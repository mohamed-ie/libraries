package io.github.mohamed_ie.sample.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import io.github.mohamed_ie.sample.home.state.LoadingState
import io.github.mohamed_ie.sample.home.state.SuccessState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    translate: () -> Unit
) {
    AnimatedContent(
        targetState = uiState,
        contentKey = { it::class.simpleName }
    ) { targetState ->
        when (targetState) {
            HomeUiState.Loading -> LoadingState()
            is HomeUiState.Success -> SuccessState(
                uiState = targetState,
                translate = translate
            )
        }
    }
}