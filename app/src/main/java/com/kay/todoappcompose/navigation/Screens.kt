package com.kay.todoappcompose.navigation

import androidx.navigation.NavHostController
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants.LIST_SCREEN

// Class which handle our navigation
// This screen class should contains navController
class Screens(navController: NavHostController) {
    // Defined two variable screens below that represent one screen

    // 1
    val list: (Action) -> Unit = { action ->
        // make our Enum action to navigate
        // navigating to the destination
        navController.navigate("list/${action.name}") {
            // Define which destination we are going to.
            popUpTo(LIST_SCREEN) {
                inclusive =
                    true // <- remove the task from the back stack when we navigate from task to list
            } /*Whenever we navigate from our task  to our list, we pop up to our list screen */
        }
    }

    // 2  we just want to pass the specific Id task and not the whole task object
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId") // The navController will navigate us to our taskComposable with the specify ID
    }
}
