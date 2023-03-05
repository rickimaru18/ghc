package com.bitflyer.ghc.ui.composables.tiles

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.ui.composables.Avatar
import com.bitflyer.ghc.ui.theme.GHCTheme

@Composable
fun UserTile(
    modifier: Modifier = Modifier,
    user: User,
) = Card(
    modifier = modifier
        .fillMaxWidth(),
    elevation = 5.dp
) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(user.avatarUrl)
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = user.username
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = GHCTheme {
    UserTile(
        user = User(
            id = 1,
            username = "Sample User",
            avatarUrl = "https://avatars.githubusercontent.com/u/86637727?v=4",
        )
    )
}