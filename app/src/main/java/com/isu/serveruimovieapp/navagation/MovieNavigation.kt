package com.isu.serveruimovieapp.navagation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isu.serveruimovieapp.screens.StartScreen
import com.isu.serveruimovieapp.screens.details.DetailsScreen
import com.isu.serveruimovieapp.screens.home.HomeScreen
import com.isu.serveruimovieapp.screens.home.HomeViewModel

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(navController = navController, startDestination = MovieScreens.StartScreen.name){
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)

        }
        composable(MovieScreens.StartScreen.name){
            StartScreen(navController = navController)
        }
        composable(MovieScreens.DetailsScreen.name+"/{id}",
        arguments = listOf(navArgument(name = "id") {type = NavType.StringType})) {
            backStackEntry ->

        DetailsScreen(navController = navController,
            backStackEntry.arguments?.getString("id"))
    }
    }
}