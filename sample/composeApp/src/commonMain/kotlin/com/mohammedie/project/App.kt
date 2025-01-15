package com.mohammedie.project

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.utils.app_event.LocalAppEventInvoker
import com.compose.utils.app_event.ObserveAppEvents
import com.mohammedie.project.navigation.SmapleNavHost
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val viewModel: AppViewModel = viewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    MaterialTheme {
        CompositionLocalProvider(LocalAppEventInvoker provides viewModel) {

            ObserveAppEvents<AppEvent> { event ->
                when (event) {
                    is AppEvent.ShowSnackbar ->
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = event.message,
                                actionLabel = event.actionLabel
                            )
                            if (result == SnackbarResult.ActionPerformed) event.action?.invoke()
                        }
                }
            }

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {
                SmapleNavHost()
            }
        }
    }
}