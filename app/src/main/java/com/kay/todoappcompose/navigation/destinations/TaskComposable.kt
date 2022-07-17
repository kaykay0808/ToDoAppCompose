package com.kay.todoappcompose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
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
    ) /* Whenever we navigate from our listScreen to our taskScreen,
    then we are going to receive a taskId, and that ID will be used to request the specific task from our database.
    If the ID is not -1 (which is the case when we click the floating button)
    then we will get the specific taskId and our taskScreen will recompose with the new value,
    which we received from our selected task
    */
    { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        // (Note: Changed taskId to selected task cause collectAsState sometimes run after launchedEffect)
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskField(selectedTask = selectedTask)
            }
        }
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreens = navigateToListScreen
        )
    }
}
