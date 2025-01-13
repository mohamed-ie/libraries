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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Default implementation of [AppEventInvoker] that uses a [CoroutineScope] for managing event emissions.
 *
 * This class provides a thread-safe way to emit and observe events using a [kotlinx.coroutines.flow.SharedFlow].
 * It can be used to broadcast events to multiple subscribers in a Compose-based application.
 *
 * @property coroutineScope The [CoroutineScope] used for launching coroutines to emit events.
 *                          Defaults to [MainScope].
 */
public open class DefaultAppEventInvoker(
    private val coroutineScope: CoroutineScope = MainScope()
) : AutoCloseable, AppEventInvoker {

    /**
     * Internal mutable flow of events that can be emitted.
     */
    private val _event = MutableSharedFlow<Any>()

    /**
     * Exposed shared flow of events for observers to collect.
     */
    override val event: Flow<Any> = _event.asSharedFlow()

    /**
     * Sends a new event to the flow.
     *
     * @param event The event to be emitted.
     * @return A [kotlinx.coroutines.Job] representing the coroutine that emits the event.
     */
    override fun send(event: Any): Job = coroutineScope.launch { _event.emit(event) }

    /**
     * Closes the invoker by cancelling its [CoroutineScope].
     * This stops any ongoing or future event emissions.
     */
    override fun close() {
        coroutineScope.cancel()
    }
}