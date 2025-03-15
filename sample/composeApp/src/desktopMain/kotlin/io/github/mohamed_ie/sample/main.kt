package io.github.mohamed_ie.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sample",
    ) {
        App()
    }
}