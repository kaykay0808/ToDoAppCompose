package com.kay.todoappcompose.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kay.todoappcompose.ui.theme.topAppBarBackgroundColor
import com.kay.todoappcompose.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar(){
    DefaultListAppBar()
}

@Composable
fun DefaultListAppBar(){
    TopAppBar(
        title = {
            Text(
                text = "Task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
@Preview
fun DefaultAppBarPreview(){
    DefaultListAppBar()
}