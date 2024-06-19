package com.seungsu.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seungsu.presentation.Destination.HOME
import com.seungsu.presentation.home.HomeScreen

object Destination {
    const val HOME = "home"
    const val DDAY = "dday"
}

@Composable
fun DongsaniNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(HOME) {
            HomeScreen()
        }
    }
}
