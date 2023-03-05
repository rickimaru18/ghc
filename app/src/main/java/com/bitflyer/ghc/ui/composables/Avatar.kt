package com.bitflyer.ghc.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Avatar(
    imageUrl: String,
    size: Dp = 50.dp
) = GlideImage(
    modifier = Modifier.size(size),
    model = imageUrl,
    contentDescription = imageUrl
)