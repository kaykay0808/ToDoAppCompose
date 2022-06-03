package com.kay.todoappcompose.util

// Some action that will represent some of the actions which we are going to trigger with our database table
// So we are going to use this Enum class to actually pass the action from our task composable
enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Action {
    return if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
}
