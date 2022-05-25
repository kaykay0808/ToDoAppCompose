package com.kay.todoappcompose.navigation

import androidx.navigation.NavHostController
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    // Defined two variable screens below that represent one screen
    val list: (Action) -> Unit = { action ->
        // make our Enum action to navigate
        navController.navigate("list/${action.name}") {
            // Define which destination we are going to.
            popUpTo(LIST_SCREEN) {
                inclusive = true
            } /*Whenever we navigate from our task  to our list, we pop up to our list screen */
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId") // <- pass in our constants later
    }
}
