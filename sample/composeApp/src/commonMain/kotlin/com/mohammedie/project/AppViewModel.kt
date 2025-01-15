package com.mohammedie.project

import androidx.compose.material.SnackbarDuration
import androidx.lifecycle.ViewModel
import com.compose.utils.app_event.AppEventInvoker
import com.compose.utils.app_event.DefaultAppEventInvoker
import com.mohammedie.project.di.AppServiceLocator
import kotlin.time.Duration

class AppViewModel() : BaseViewModel()

sealed interface AppEvent {
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null,
        val duration: SnackbarDuration = actionLabel?.let { SnackbarDuration.Indefinite } ?: SnackbarDuration.Short,
        val action: (() -> Unit)? = null
    ) : AppEvent

}