package com.mohammedie.project.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    result: String?,
    transalte: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "From the river to the sea, Palestine will be free",
            style = MaterialTheme.typography.h6
        )

        result?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6
            )
        }

        Button(onClick = transalte) {
            Text(text = "Transalte")
        }
    }
}