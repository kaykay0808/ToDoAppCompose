package com.kay.todoappcompose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBarScreen(
                selectedTask = selectedTask,
                navigateToListScreens = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreens(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreens(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = titleObserved,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                description = descriptionObserved,
                onDescriptionChange = { sharedViewModel.description.value = it },
                priority = priorityObserved,
                onPrioritySelected = { sharedViewModel.priority.value = it }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Text Fields Empty",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
@Preview
fun TaskScreenPreview() {}
