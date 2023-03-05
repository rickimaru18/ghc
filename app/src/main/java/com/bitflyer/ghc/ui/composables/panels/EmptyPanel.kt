package com.bitflyer.ghc.ui.composables.panels

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyPanel(
    onReload: (() -> Unit)? = null,
    text: String,
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text)

        if (onReload != null) {
            OutlinedButton(onClick = onReload) {
                Text("Reload")
            }
        }
    }
}
