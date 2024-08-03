package com.seungsu.donsani

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sparring.profile.SparringProfileScreen
import com.seungsu.donsani.Destination.EXERCISE_GRASS
import com.seungsu.record.ExerciseRecordScreen
import com.seungsu.donsani.Destination.EXERCISE_RECORD
import com.seungsu.donsani.Destination.EXERCISE_SETTING
import com.seungsu.donsani.Destination.SPARRING_PROFILE
import com.seungsu.grass.ExerciseGrassScreen
import com.seungsu.setting.ExerciseSettingScreen

object Destination {
    const val EXERCISE_RECORD = "exercise_record"
    const val EXERCISE_GRASS = "exercise_grass"
    const val EXERCISE_SETTING = "exercise_setting"
    const val SPARRING_PROFILE = "sparring_profile"
}

@Composable
fun DongsaniNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SPARRING_PROFILE
    ) {
        composable(SPARRING_PROFILE) {
            SparringProfileScreen()
        }
        composable(EXERCISE_RECORD) {
            ExerciseRecordScreen(
                navToExerciseGrass = {
                    navController.navigate(EXERCISE_GRASS) {
                        launchSingleTop = true
                    }
                },
                navigateToSetting = {
                    navController.navigate(EXERCISE_SETTING) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(EXERCISE_GRASS) {
            ExerciseGrassScreen(
                navToExerciseRecord = {
                    navController.navigate(EXERCISE_RECORD) {
                        launchSingleTop = true
                    }
                },
                navigateToSetting = {
                    navController.navigate(EXERCISE_SETTING) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(EXERCISE_SETTING) {
            ExerciseSettingScreen(
                onNavPopback = { navController.popBackStack() }
            )
        }
    }
}
