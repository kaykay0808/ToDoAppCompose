package com.kay.todoappcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kay.todoappcompose.ui.screens.list.ListScreen
import com.kay.todoappcompose.util.Constants.LIST_ARGUMENT_KEY
import com.kay.todoappcompose.util.Constants.LIST_SCREEN

// extension function listComposable
// LIST SCREEN
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        // specify what argument the screen will have
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY) {
                type = NavType.StringType
            }
        )
    ) {
        // instead of designing our screen here we will create it in a separated file
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}
