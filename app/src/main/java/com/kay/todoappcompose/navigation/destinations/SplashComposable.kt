package com.kay.todoappcompose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.composable
import com.kay.todoappcompose.ui.screens.splash.SplashScreen
import com.kay.todoappcompose.util.Constants.SPLASH_SCREEN

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        // Exit animation
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight ->
                    -fullHeight
                },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SplashScreen(
            navigateToListScreen = navigateToListScreen
        )
    }
}

