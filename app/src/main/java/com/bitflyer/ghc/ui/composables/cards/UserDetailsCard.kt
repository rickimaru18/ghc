package com.bitflyer.ghc.ui.composables.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitflyer.ghc.domain.models.UserDetails
import com.bitflyer.ghc.ui.composables.Avatar

@Composable
fun UserDetailsCard(user: UserDetails) = Card(
    modifier = Modifier
        .fillMaxWidth(),
    elevation = 3.dp
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        NameSection(user)
        LocationSection(user)
        CountSection(user)
    }
}

@Composable
private fun NameSection(user: UserDetails) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(20.dp)
) {
    Avatar(
        user.avatarUrl,
        size = 100.dp
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (user.name != null) Text(
            text = user.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        
        Text(text = user.username)
        Text(text = user.email ?: "")
    }
}

@Composable
private fun LocationSection(user: UserDetails) {
    if (user.location?.isNotBlank() == true) LocationItem(
        title = "Location",
        text = user.location
    )

    if (user.company?.isNotBlank() == true) LocationItem(
        title = "Company",
        text = user.company
    )
}

@Composable
private fun LocationItem(
    title: String,
    text: String,
) = Column(
    verticalArrangement = Arrangement.spacedBy(5.dp)
) {
    Text(
        title,
        fontWeight = FontWeight.SemiBold
    )
    Text(text)
}

@Composable
private fun CountSection(user: UserDetails) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceAround
) {
    CountItem(
        text = "Public Repos",
        count = user.publicRepos
    )
    CountItem(
        text = "Followers",
        count = user.followers
    )
    CountItem(
        text = "Following",
        count = user.following
    )
}

@Composable
private fun CountItem(
    text: String,
    count: Int
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(5.dp)
) {
    Text(text)
    Text(count.toString())
}