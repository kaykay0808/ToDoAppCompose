package com.kay.todoappcompose.navigation

import androidx.navigation.NavHostController
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants.LIST_SCREEN
import com.kay.todoappcompose.util.Constants.SPLASH_SCREEN

// Class which handle our navigation
// This screen class should contains navController
class ScreenHolder(navController: NavHostController) {

    // Defined variable screens below that represent one screen
    /** SPLASH-SCREEN TO LIST-SCREEN */
    val splashRoute: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    // we just want to pass the specific Id task and not the whole task object
    /** LIST-SCREEN TO TASK-SCREEN  */
    val listRoute: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId") // The navController will navigate us to our taskComposable with the specify ID
    }

    /** TASK-SCREEN TO LIST-SCREEN */
    val taskRoute: (Action) -> Unit = { action ->
        // make our Enum action to navigate
        // navigating to the destination
        navController.navigate("list/${action.name}") {
            // Define which destination we are going to.
            popUpTo(LIST_SCREEN) {
                inclusive =
                    true // <- remove the task from the back stack when we navigate from task to list
            } /*Whenever we navigate from our task  to our list. TaskScreen will be removed from back stack so we can´t move back to task screen*/
        }
    }
}
