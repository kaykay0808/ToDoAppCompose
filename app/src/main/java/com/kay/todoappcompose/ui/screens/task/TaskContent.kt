package com.kay.todoappcompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.R
import com.kay.todoappcompose.components.PriorityDropDown
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.ui.theme.LARGE_PADDING
import com.kay.todoappcompose.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize() /* takes the whole content in the Scaffold */
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)

    ) {
        // TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(id = R.string.title)) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true // Allows the user to only write in 1 line
        )
        // Divider will basically just add some empty space between OutlinedTextField and PriorityDropDown
        Divider(
            modifier = Modifier
                .height(MEDIUM_PADDING),
            color = MaterialTheme.colors.background
        )

        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = stringResource(id = R.string.description)) },
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Composable
@Preview
fun TaskContentPreview() {
    TaskContent(
        title = "Nice",
        onTitleChange = {},
        description = "Some Random text",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}
