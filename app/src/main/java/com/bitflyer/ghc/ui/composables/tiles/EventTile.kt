package com.bitflyer.ghc.ui.composables.tiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.Org
import com.bitflyer.ghc.ui.composables.Avatar

@Composable
fun EventTile(event: Event) = Column {
    RepoSection(event)

    if (event.org != null) OrgSection(event.org)
}

@Composable
private fun RepoSection(event: Event) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Repo",
            fontWeight = FontWeight.SemiBold
        )
        Text(event.type.toString())
    }
    Text(event.repo.name)
}

@Composable
private fun OrgSection(org: Org) {
    Text(
        "Org",
        fontWeight = FontWeight.SemiBold
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Avatar(org.avatarUrl)
        Text(org.username)
    }
}