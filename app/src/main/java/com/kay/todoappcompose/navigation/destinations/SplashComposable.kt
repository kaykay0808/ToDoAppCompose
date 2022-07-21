package com.kay.todoappcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kay.todoappcompose.ui.screens.splash.SplashScreen
import com.kay.todoappcompose.util.Constants

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,


    ) {
        SplashScreen(
            navigateToListScreen = navigateToListScreen
        )
    }
}