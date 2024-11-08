package com.rahim.harmony.navigation

import androidx.annotation.DrawableRes
import com.rahim.harmony.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val iconNormal: Int?,
    @DrawableRes val iconSelected: Int?,
) {
    data object Home : BottomNavItem(
        Destinations.Home.route,
        R.drawable.outline_home_24,
        R.drawable.baseline_home_24,
    )
    data object Explore : BottomNavItem(
        Destinations.Explore.route,
        R.drawable.outline_explore_24,
        R.drawable.baseline_explore_24,
    )
    data object Library : BottomNavItem(
        Destinations.Library.route,
        R.drawable.outline_library_music_24,
        R.drawable.baseline_library_music_24,
    )
}
