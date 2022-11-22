package com.isu.serveruimovieapp.navagation

enum class MovieScreens {
    HomeScreen,
    StartScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): MovieScreens
        = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalAccessException("Route $route is not valid")
        }
    }
}