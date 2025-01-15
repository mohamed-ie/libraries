package com.mohammedie.project.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.utils.app_event.ObserveAppEvents

@Composable
fun HomeRoute(viewModel: HomeViewModel = viewModel()) {
    var result by remember { mutableStateOf<String?>(null) }

    ObserveAppEvents<HomeUiEvent>(invoker = viewModel) { event ->
        when (event) {
            is HomeUiEvent.ShowResult -> result = event.result
        }
    }

    HomeScreen(
        result = result,
        transalte = viewModel::transalte
    )
}