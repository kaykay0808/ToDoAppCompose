package com.kay.todoappcompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.ui.viewmodels.SharedViewModel
import com.kay.todoappcompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreens: (Action) -> Unit

) {
    // Observing values from our viewModel. (Title, Description, Priority)
    val titleObserved: String by sharedViewModel.title
    val descriptionObserved: String by sharedViewModel.description
    val priorityObserved: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBarScreen(
                selectedTask = selectedTask,
                navigateToListScreens = navigateToListScreens
            )
        },
        content = {  TaskContent(
            title = titleObserved,
            onTitleChange = {sharedViewModel.title.value = it},
            description = descriptionObserved,
            onDescriptionChange = {sharedViewModel.description.value = it},
            priority = priorityObserved,
            onPrioritySelected = {sharedViewModel.priority.value = it}
        )}
    )
}

@Composable
@Preview
fun TaskScreenPreview() {
}
