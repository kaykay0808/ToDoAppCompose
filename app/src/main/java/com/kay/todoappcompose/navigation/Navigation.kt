package com.kay.todoappcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kay.todoappcompose.navigation.destinations.listComposable
import com.kay.todoappcompose.navigation.destinations.splashComposable
import com.kay.todoappcompose.navigation.destinations.taskComposable
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Constants.SPLASH_SCREEN

// Settings for navigation
// NavHost

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    // save our backstack
    // The variable to keep track of all our composable
    val screen = remember(navController) {
        ScreenHolder(navController = navController)
    }

    // Calling the navHost which define the navigation graph.
    // Changed AnimatedNavHost from NavHost
    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        // Define our composable build (we will create our custom destination instead of define our composable below)
        splashComposable(
            navigateToListScreen = screen.splashRoute
        )
        listComposable(
            navigateToTaskScreen = screen.listRoute,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.taskRoute,
            sharedViewModel = sharedViewModel
        )
    }
}
