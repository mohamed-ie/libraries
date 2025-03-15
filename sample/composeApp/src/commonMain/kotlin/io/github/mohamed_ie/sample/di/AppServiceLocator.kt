package io.github.mohamed_ie.sample.di

import com.compose.utils.app_event.AppEventInvoker
import com.compose.utils.app_event.DefaultAppEventInvoker

object AppServiceLocator {
    val appEventInvoker: AppEventInvoker by lazy { DefaultAppEventInvoker() }
}