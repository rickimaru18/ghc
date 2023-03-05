package com.bitflyer.ghc.ui.composables.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun PaginatedList(
    onNext: () -> Unit,
    loadingIndicator: (@Composable () -> Unit)? = null,
    itemCount: Int,
    hasNextPage: Boolean,
    headerItems: (LazyListScope.() -> Unit)? = null,
    itemBuilder: @Composable (index: Int) -> Unit
) = LazyColumn(
    contentPadding = PaddingValues(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    headerItems?.invoke(this)

    items(
        count = itemCount + if (hasNextPage) 1 else 0
    ) { i ->
        if (i + 1 == itemCount + 1) {
            onNext()
        }

        if (hasNextPage && i == itemCount) {
            loadingIndicator?.invoke() ?: CircularProgressIndicator()
        } else {
            itemBuilder(i)
        }
    }
}
