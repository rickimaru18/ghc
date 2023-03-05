package com.bitflyer.ghc.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.ui.composables.lists.PaginatedList
import com.bitflyer.ghc.ui.composables.panels.EmptyPanel
import com.bitflyer.ghc.ui.composables.panels.ErrorPanel
import com.bitflyer.ghc.ui.composables.tiles.UserTile
import com.bitflyer.ghc.ui.theme.GHCTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    onNavigateToUser: (user: User) -> Unit,
    viewModel: HomeViewModel,
) = Scaffold(
    topBar = { AppBar() }
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (viewModel.isLoading && viewModel.userCount() == 0) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            UserList(
                onNavigateToUser = onNavigateToUser,
                viewModel = viewModel,
            )
        }
    }
}

@Composable
private fun AppBar() = TopAppBar(
    contentPadding = PaddingValues(start = 20.dp)
) {
    Text(text = "GitHub Client - Users")
}

@Composable
private fun UserList(
    onNavigateToUser: (user: User) -> Unit,
    viewModel: HomeViewModel,
) {
    val users: List<User>? = viewModel.users

    if (users == null) {
        return ErrorPanel(
            onReload = { viewModel.getAllUsers() },
            text = "Failed loading users."
        )
    } else if (users!!.isEmpty()) {
        return EmptyPanel(
            onReload = { viewModel.getAllUsers() },
            text = "No users found."
        )
    }

    PaginatedList(
        onNext = { viewModel.nextPage() },
        itemCount = users!!.size,
        hasNextPage = viewModel.hasNextPage,
    ) {
        val user: User = users!![it]

        UserTile(
            modifier = Modifier.clickable { onNavigateToUser(user) },
            user = user
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = GHCTheme {
    HomePage(
        onNavigateToUser = {},
        viewModel = HomeViewModel()
    )
}