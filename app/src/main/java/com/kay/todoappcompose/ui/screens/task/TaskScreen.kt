package com.kay.todoappcompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.util.Action

@Composable
fun TaskScreen (
    navigateToListScreens: (Action) -> Unit
) {

    Scaffold(
        topBar =  {
            TaskAppBarScreen(navigateToListScreens = navigateToListScreens)
        },
        content = {}
    )
}

@Composable
@Preview
fun TaskScreenPreview() {

}