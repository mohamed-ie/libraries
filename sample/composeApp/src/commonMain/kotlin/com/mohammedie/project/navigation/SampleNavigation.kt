package com.mohammedie.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammedie.project.home.HomeRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object HomeScreen

@Composable
fun SmapleNavHost(
    modifier: Modifier = Modifier,
    startDestination: KClass<*> = HomeScreen::class,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<HomeScreen> {
            HomeRoute()
        }
    }
}