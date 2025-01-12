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

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing an event invoker used to emit and observe application-wide events.
 *
 * This interface is typically implemented in scenarios where events need to be broadcasted
 * and observed across different parts of an application, such as in a Compose-based project.
 */
interface AppEventInvoker {

    /**
     * A Flow of events that observers can collect to react to specific events.
     *
     * This property provides a continuous stream of events that can be filtered or transformed
     * based on the type or content of the events.
     */
    val event: Flow<Any>

    /**
     * Sends a new event to the event stream.
     *
     * @param event The event object to be emitted. It can be any type, allowing flexibility
     *              in the types of events that can be sent.
     * @return A [Job] representing the coroutine responsible for emitting the event.
     *         This allows callers to manage or await the completion of the event emission if necessary.
     */
    fun send(event: Any): Job
}