package com.rahim.harmony.navigation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.rahim.harmony.feature.home.navigation.homeScreen
import com.rahim.harmony.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    destination: String?,
) {
    val screenItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Library,
    )
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground,
    ) {
        screenItems.forEach { screen ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigateSingleTopTo(screen.route)
                },
                icon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.secondary,
                        painter = painterResource(
                            id = if (destination == screen.route) screen.iconSelected
                                ?: 0 else screen.iconNormal ?: 0
                        ),
                        contentDescription = screen.route
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
            )
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues,
) {
    NavHost(navController, startDestination = startDestination, Modifier.padding(innerPadding)) {
        homeScreen()
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }