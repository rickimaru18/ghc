package com.bitflyer.ghc.ui.composables.panels

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitflyer.ghc.ui.theme.GHCTheme

@Composable
fun ErrorPanel(
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
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Rounded.Warning,
            contentDescription = "error",
            tint = Color.Red,
        )
        Text(text)

        if (onReload != null) {
            OutlinedButton(onClick = onReload) {
                Text("Reload")
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() = GHCTheme {
    ErrorPanel(
        onReload = {},
        text = "Error text"
    )
}