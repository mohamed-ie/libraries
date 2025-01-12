/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.compose.utils.app_event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.filterIsInstance

/**
 * CompositionLocal that provides the current [AppEventInvoker] instance.
 * Defaults to `null` if no instance is provided in the composition tree.
 */
val LocalAppEventInvoker = compositionLocalOf<AppEventInvoker?> { null }

/**
 * Observes events of a specific type emitted by the [AppEventInvoker].
 *
 * This function filters events based on their type and triggers the specified [onEvent] callback
 * only for events of type [T].
 *
 * @param T The type of events to observe.
 * @param invoker The [AppEventInvoker] to observe. Defaults to the current value of [LocalAppEventInvoker].
 * @param onEvent A callback invoked for each emitted event of type [T].
 */
@Composable
inline fun <reified T : Any> ObserveAppEvents(
    invoker: AppEventInvoker? = LocalAppEventInvoker.current,
    noinline onEvent: (T) -> Unit
) {
    LaunchedEffect(invoker) {
        invoker?.event
            ?.filterIsInstance<T>()
            ?.collect(onEvent)
    }
}