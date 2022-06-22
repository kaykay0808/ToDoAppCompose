package com.kay.todoappcompose.navigation.destinations

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kay.todoappcompose.ui.screens.task.TaskScreen
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants
import com.kay.todoappcompose.util.Constants.TASK_ARGUMENT_KEY
import com.kay.todoappcompose.util.Constants.TASK_SCREEN
import kotlinx.coroutines.flow.collect

// TASK SCREEN DESTINATION
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        // specify what argument the screen will have
        arguments = listOf(
            navArgument(Constants.TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        TaskScreen(
            selectedTask = selectedTask,
            navigateToListScreens = navigateToListScreen
        )
    }
}
