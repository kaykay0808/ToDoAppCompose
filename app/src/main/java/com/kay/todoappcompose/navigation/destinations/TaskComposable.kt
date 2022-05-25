package com.kay.todoappcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants
import com.kay.todoappcompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){
        // todo later
    }
}