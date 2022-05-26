package com.kay.todoappcompose.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.R

@Composable
fun ListScreen(navigateToTaskScreen: (Int) -> Unit) {
    Scaffold(
        // Creating the actionBar
        topBar = {
                 ListAppBar()
        },
        content = {},
        // Creating a floatingAction button
        floatingActionButton = {
            ListFloatingActionButton(onFloatingActionButtonClicked = navigateToTaskScreen)

        }
    )
}

@Composable
fun ListFloatingActionButton(onFloatingActionButtonClicked: (Int) -> Unit) {
    // Define our floating action button
    FloatingActionButton(
        onClick = {
            onFloatingActionButtonClicked(-1/*(<- Assign an id number here)*/)
        }
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
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}