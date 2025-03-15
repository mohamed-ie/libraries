package io.github.mohamed_ie.sample.home

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import io.github.mohamed_ie.sample.AppEvent
import io.github.mohamed_ie.sample.BaseViewModel
import io.github.mohamed_ie.uistate.annotations.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val ENGLISH_VALUE = "From the river to the sea, Palestine will be free"
private const val ARABIC_VALUE = "من النيل إلى الفرات، فلسطين ستكون حرة"

class HomeViewModel : BaseViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var initializeCalled = false

    @MainThread
    fun initialize() {
        if (initializeCalled) return
        initializeCalled = true
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(1000)
            _uiState.update { HomeUiState.Success("From the river to the sea, Palestine will be free.") }
        }
    }

    fun translate() {
        val uiState = _uiState.success ?: return

        viewModelScope.launch {
            send(AppEvent.ShowSnackbar(message = "Loading..."))
            delay(1000)

            if (Random.nextBoolean()) {
                val event = AppEvent.ShowSnackbar(
                    message = "Failed",
                    actionLabel = "Retry",
                    action = ::translate
                )
                send(event)
                return@launch
            }

            val result = if (uiState.result == ARABIC_VALUE) ENGLISH_VALUE else ARABIC_VALUE
            _uiState.updateSuccess { it.copy(result = result) }
        }
    }
}
