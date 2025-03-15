package io.github.mohamed_ie.sample

import androidx.compose.material.SnackbarDuration

sealed interface AppEvent {
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null,
        val duration: SnackbarDuration = actionLabel?.let { SnackbarDuration.Indefinite } ?: SnackbarDuration.Short,
        val action: (() -> Unit)? = null
    ) : AppEvent
}