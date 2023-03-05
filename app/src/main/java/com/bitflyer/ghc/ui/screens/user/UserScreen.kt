package com.bitflyer.ghc.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.UserDetails
import com.bitflyer.ghc.ui.composables.cards.UserDetailsCard
import com.bitflyer.ghc.ui.composables.lists.PaginatedList
import com.bitflyer.ghc.ui.composables.panels.ErrorPanel
import com.bitflyer.ghc.ui.composables.tiles.EventTile
import com.bitflyer.ghc.ui.theme.GHCTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserPage(
    onBack: () -> Unit,
    viewModel: UserViewModel
) = Scaffold(
    topBar = { AppBar(onBack) }
) {
    if (viewModel.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        DetailsList(viewModel)
    }
}

@Composable
private fun AppBar(onBack: () -> Unit) = TopAppBar {
    Row {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "back"
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "GH Client"
        )
    }
}

@Composable
private fun DetailsList(viewModel: UserViewModel) {
    val user: UserDetails = viewModel.user
        ?: return ErrorPanel(
            onReload = { viewModel.getUser() },
            text = "Failed fetching user details."
        )

    val events: List<Event>? = viewModel.events

    PaginatedList(
        onNext = { viewModel.nextPage() },
        itemCount = events?.size ?: 0,
        hasNextPage = viewModel.hasNextPage,
        headerItems = {
            item {
                UserDetailsCard(user)
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "Events",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    ) {
        Column {
            Divider()
            EventTile(events!![it])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = GHCTheme {
    UserPage(
        onBack = {},
        viewModel = UserViewModel("sample")
    )
}