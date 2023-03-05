package com.bitflyer.ghc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bitflyer.ghc.ui.screens.home.HomePage
import com.bitflyer.ghc.ui.screens.home.HomeViewModel
import com.bitflyer.ghc.ui.screens.user.UserPage
import com.bitflyer.ghc.ui.screens.user.UserViewModel

sealed class Route(val route: String) {
    object Home : Route("home")
    object User : Route("user")
}

@Composable
fun NavRouter(navController: NavHostController) = NavHost(
    navController = navController,
    startDestination = Route.Home.route
) {
    composable(Route.Home.route) {
        HomeRoute(navController)
    }
    composable(
        "${Route.User.route}/{username}",
        arguments = listOf(navArgument("username") { type = NavType.StringType })
    ) {
        UserRoute(navController, it)
    }
}

@Composable
private fun HomeRoute(navController: NavHostController) {
    val viewModel: HomeViewModel by remember { mutableStateOf<HomeViewModel>(HomeViewModel()) }

    HomePage(
        onNavigateToUser = {
            navController.navigate(
                route = "${Route.User.route}/${it.username}"
            )
        },
        viewModel = viewModel
    )
}

@Composable
fun UserRoute(
    navController: NavHostController,
    nav: NavBackStackEntry
) {
    val viewModel: UserViewModel by remember {
        mutableStateOf<UserViewModel>(UserViewModel(nav.arguments!!.getString("username")!!))
    }

    UserPage(
        onBack = { navController.navigateUp() },
        viewModel = viewModel
    )
}
