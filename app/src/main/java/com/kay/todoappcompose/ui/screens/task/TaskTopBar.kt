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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.R
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.ui.theme.topAppBarBackgroundColor
import com.kay.todoappcompose.ui.theme.topAppBarContentColor
import com.kay.todoappcompose.util.Action

/** -------------------TASK APP BAR---------------------------- */
@Composable
fun TaskAppBarScreen(
    selectedTask: ToDoTask?,
    navigateToListScreens: (Action) -> Unit
) {
    // switching between bars
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreens = navigateToListScreens)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreens = navigateToListScreens)
    }

}

@Composable
fun NewTaskAppBar(
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
                text = stringResource(id = R.string.add_task),
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
fun ExistingTaskAppBar(
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
            DeleteAction(onDeleteClicked = navigateToListScreens)
            UpdateAction(onUpdateClicked = navigateToListScreens)

        }
    )
}

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
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
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
    NewTaskAppBar(navigateToListScreens = {})
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 0,
            title = "Kayashiro",
            description = "He is a beast",
            priority = Priority.HIGH
        ),
        navigateToListScreens = {}
    )
}

