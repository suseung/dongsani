package com.seungsu.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seungsu.presentation.Destination.EXERCISE_GRASS
import com.seungsu.presentation.exercisegrass.ExcersiseGrassMainScreen

object Destination {
    const val EXERCISE_GRASS = "exercise_grass"
}

@Composable
fun DongsaniNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = EXERCISE_GRASS
    ) {
        composable(EXERCISE_GRASS) {
            ExcersiseGrassMainScreen()
        }
    }
}
