package com.compose.utils.app_event

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class DefaultAppEventInvokerTest {
    private lateinit var subject: AppEventInvoker
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeTest
    fun setup() {
        subject = DefaultAppEventInvoker(testScope)
    }

    @Test
    fun `when event sent then event received`() = runTest {
        val events = mutableListOf<Any>()

        backgroundScope.launch(testDispatcher) {
            subject.event.toList(events)
        }

        subject.send(Unit)

        assertTrue(events.isNotEmpty())
    }
}