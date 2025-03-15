package io.github.mohamed_ie.sample.home.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.mohamed_ie.sample.home.HomeUiState

@Composable
fun SuccessState(
    uiState: HomeUiState.Success,
    translate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(
            text = uiState.result,
            style = MaterialTheme.typography.h6
        )

        Button(onClick = translate) {
            Text(text = "Translate")
        }
    }
}