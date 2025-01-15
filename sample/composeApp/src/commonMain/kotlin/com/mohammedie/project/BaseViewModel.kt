package com.mohammedie.project

import androidx.lifecycle.ViewModel
import com.compose.utils.app_event.AppEventInvoker
import com.mohammedie.project.di.AppServiceLocator

abstract class BaseViewModel(
    private val appEventInvoker: AppEventInvoker = AppServiceLocator.appEventInvoker
) : ViewModel(), AppEventInvoker by appEventInvoker