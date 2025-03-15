package io.github.mohamed_ie.sample

import androidx.lifecycle.ViewModel
import com.compose.utils.app_event.AppEventInvoker
import io.github.mohamed_ie.sample.di.AppServiceLocator

abstract class BaseViewModel(
    private val appEventInvoker: AppEventInvoker = AppServiceLocator.appEventInvoker
) : ViewModel(), AppEventInvoker by appEventInvoker