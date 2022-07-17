package com.kay.todoappcompose.ui.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kay.todoappcompose.R
import com.kay.todoappcompose.ui.theme.floatingActionButtonBackgroundColor
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTask()
    }

    // observing some variable
    val action by sharedViewModel.action

    val allTask by sharedViewModel.allTask.collectAsState()
    val searchedTask by sharedViewModel.searchedTask.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    // sharedViewModel.handleDatabaseActions(action = action)
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModel.handleDatabaseActions(action = action) },
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        // Creating the actionBar
        topBar = {
            AppBarListScreen(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            // todo: creating a list content kotlin file.
            // todo: define our task item composable function, which will represent one row in a list.
            ListContent(
                allTask = allTask,
                searchedTask = searchedTask,
                searchAppBarState = searchAppBarState,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        // Creating a floatingAction button
        floatingActionButton = {
            ListFloatingActionButton(onFloatingActionButtonClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFloatingActionButton(
    onFloatingActionButtonClicked: (taskId: Int) -> Unit
) {
    // Define our floating action button
    FloatingActionButton(
        onClick = {
            onFloatingActionButtonClicked(-1/*(<- Assign an id number here)*/)
        },
        backgroundColor = MaterialTheme.colors.floatingActionButtonBackgroundColor
    ) {
        // Add an icon to our floating action button
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState, /* scaffold state represent our a state of our scaffold composable component */
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Task Removed."
        else -> {
            "${action.name} : $taskTitle"
        }
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}
