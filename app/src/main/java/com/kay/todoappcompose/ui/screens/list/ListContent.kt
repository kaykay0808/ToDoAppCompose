package com.kay.todoappcompose.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.ui.theme.LARGE_PADDING
import com.kay.todoappcompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.kay.todoappcompose.ui.theme.TASK_ITEM_ELEVATION
import com.kay.todoappcompose.ui.theme.taskItemBackgroundColor
import com.kay.todoappcompose.ui.theme.taskItemTextColor
import com.kay.todoappcompose.util.RequestState
import com.kay.todoappcompose.util.SearchAppBarState

// TODO: (1) Creating a list content kotlin file.
// TODO: (2) Define our task item composable function, which will represent one row in a list.

@ExperimentalMaterialApi
@Composable
fun ListContent(
    allTask: RequestState<List<ToDoTask>>,
    searchedTask: RequestState<List<ToDoTask>>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    // If we clicked on the search symbol in the keyboard
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedTask is RequestState.Success) {
            HandleListContent(
                task = searchedTask.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    } else {
        if (allTask is RequestState.Success) {
            HandleListContent(
                task = allTask.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    task: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (task.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTask(
            task = task,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayTask(
    task: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = task,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor, /* this is the white background which will change when we choose the dark theme*/
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id) /* Navigate to our task screen along with the task ID */
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis /* This value will give dots in the end of text if line is more then 2*/
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            0,
            "Title",
            "Description",
            priority = Priority.MEDIUM
        ),
        navigateToTaskScreen = {}
    )
}
