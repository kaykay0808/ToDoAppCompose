package com.kay.todoappcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kay.todoappcompose.navigation.destinations.listComposable
import com.kay.todoappcompose.navigation.destinations.taskComposable
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Constants.LIST_SCREEN

// Settings for navigation
// NavHost

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    // save our backstack
    // The variable to keep track of all our composable
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    // Calling the navHost which define the navigation graph.
    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        // Define our composable build (we will create our custom destination instead of define our composable below)
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}
