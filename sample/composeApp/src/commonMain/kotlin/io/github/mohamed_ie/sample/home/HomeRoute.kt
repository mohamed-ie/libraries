package io.github.mohamed_ie.sample.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.utils.app_event.ObserveAppEvents

@Composable
fun HomeRoute(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.initialize()
    }

    HomeScreen(
        uiState = uiState,
        translate = viewModel::translate
    )
}