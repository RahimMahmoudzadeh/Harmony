package com.rahim.harmony.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rahim.harmony.core.base.use
import com.rahim.harmony.designsystem.theme.HarmonyTheme
import com.rahim.harmony.feature.home.HomeScreenRoute
import com.rahim.harmony.navigation.Destinations
import com.rahim.harmony.navigation.component.BottomNavigationBar
import com.rahim.harmony.navigation.component.NavigationComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val (state, event) = use(viewModel)
            LaunchedEffect(true) {
                event.invoke(MainContract.MainEvent.RefreshMusic)
            }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            HarmonyTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomNavigationBar(navController, navBackStackEntry, currentRoute)
                }) { innerPadding ->
                    NavigationComponent(navController, Destinations.Home.route, innerPadding)
                }
            }
        }
    }
}
