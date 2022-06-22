package com.kay.todoappcompose.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kay.todoappcompose.ui.screens.list.ListScreen
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Constants.LIST_ARGUMENT_KEY
import com.kay.todoappcompose.util.Constants.LIST_SCREEN

// extension function listComposable
// Navigation graph
// ListComposable Destination
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN, // <- "list/{action}"
        // specify what argument the screen will have
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY) {
                // specify the argument type which will be a string
                type = NavType.StringType
            }
        )
    ) {
        // instead of designing our screen here we will create it in a separated file
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
