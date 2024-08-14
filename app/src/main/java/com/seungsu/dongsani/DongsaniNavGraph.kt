package com.seungsu.dongsani

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sparring.makeprofile.SparringMakeProfileScreen
import com.jakewharton.processphoenix.ProcessPhoenix
import com.seungsu.common.model.ContentsType
import com.seungsu.dongsani.Destination.EXERCISE_GRASS
import com.seungsu.record.ExerciseRecordScreen
import com.seungsu.dongsani.Destination.EXERCISE_RECORD
import com.seungsu.dongsani.Destination.EXERCISE_SETTING
import com.seungsu.dongsani.Destination.SPARRING_PROFILE
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
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf(SPARRING_PROFILE) }

    LaunchedEffect(Unit) {
        viewModel.currentContent.collect {
            startDestination = when (it) {
                ContentsType.SPARRING.code -> SPARRING_PROFILE
                ContentsType.EXERCISE_RECORD.code -> EXERCISE_RECORD
                else -> SPARRING_PROFILE
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(SPARRING_PROFILE) {
            SparringMakeProfileScreen(
                onRestart = {
                    val newIntent = Intent((context as Activity), MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    ProcessPhoenix.triggerRebirth(context.applicationContext, newIntent)
                }
            )
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
                },
                onRestart = {
                    val newIntent = Intent((context as Activity), MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    ProcessPhoenix.triggerRebirth(context.applicationContext, newIntent)
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
