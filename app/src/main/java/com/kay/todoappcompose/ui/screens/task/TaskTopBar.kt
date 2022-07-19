package com.kay.todoappcompose.ui.screens.task

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.R
import com.kay.todoappcompose.components.DisplayAlertDialog
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.ui.theme.topAppBarBackgroundColor
import com.kay.todoappcompose.ui.theme.topAppBarContentColor
import com.kay.todoappcompose.util.Action

/** -------------------TASK APP BAR---------------------------- */
@Composable
fun TaskTopBar(
    selectedTask: ToDoTask?,
    navigateToListScreens: (Action) -> Unit
) {
    // switching between newTaskTopBar and existingTopBar
    if (selectedTask == null) {
        NewTaskTopBar(
            navigateToListScreens = navigateToListScreens
        )
    } else {
        ExistingTaskTopBar(
            selectedTask = selectedTask,
            navigateToListScreens = navigateToListScreens
        )
    }
}

// When floating button is clicked NewTaskBar will display
@Composable
fun NewTaskTopBar(
    navigateToListScreens: (Action) -> Unit,
) {
    // Designing the surface/topBar
    TopAppBar(
        navigationIcon = {
            // designing a back action icon function
            BackAction(onBackClicked = navigateToListScreens)
        },
        title = {
            Text(
                text = stringResource(id = R.string.new_task_screen_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreens)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

/** -------------------Existing APP BAR---------------------------- */

@Composable
fun ExistingTaskTopBar(
    selectedTask: ToDoTask,
    navigateToListScreens: (Action) -> Unit,
) {
    // Designing the surface/topBar
    TopAppBar(
        navigationIcon = {
            // designing a close action icon function
            CloseAction(onCloseClicked = navigateToListScreens)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis /* Adds 3 dots */
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigateToListScreens = navigateToListScreens
            )
        }
    )
}

// Actions for our ExistingTaskTopBar
@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreens: (Action) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })

    DisplayAlertDialog(
        title = stringResource(
            id = R.string.delete_task,
            selectedTask.title
        ),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title
        ),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigateToListScreens(Action.DELETE) }
    )

    UpdateAction(onUpdateClicked = navigateToListScreens)
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskTopBar(navigateToListScreens = {})
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskTopBar(
        selectedTask = ToDoTask(
            id = 0,
            title = "Existing task",
            description = "He is a beast",
            priority = Priority.HIGH
        ),
        navigateToListScreens = {}
    )
}
