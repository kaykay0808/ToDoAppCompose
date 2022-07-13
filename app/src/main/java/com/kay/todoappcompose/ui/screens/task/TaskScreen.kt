package com.kay.todoappcompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreens: (Action) -> Unit
) {

    Scaffold(
        topBar = {
            TaskAppBarScreen(
                selectedTask = selectedTask,
                navigateToListScreens = navigateToListScreens
            )
        },
        content = { TaskContent(
            title = "",
            onTitleChange = {},
            description = "",
            onDescriptionChange = {},
            priority = Priority.LOW,
            onPrioritySelected = {}
        )}
    )
}

@Composable
@Preview
fun TaskScreenPreview() {
}
