package com.rahim.harmony.navigation

sealed class Destinations(val route:String) {

    data object Home : Destinations(ModuleRoutes.HOME.route)
    data object Explore : Destinations(ModuleRoutes.EXPLORE.route)
    data object Library : Destinations(ModuleRoutes.LIBRARY.route)
    data object MusicDetail : Destinations(ModuleRoutes.MUSIC_DETAIL.route)


}
private enum class ModuleRoutes(val route: String) {
    HOME("home"),
    EXPLORE("explore"),
    LIBRARY("library"),
    MUSIC_DETAIL("music detail"),
}