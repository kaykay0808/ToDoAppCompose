package com.kay.todoappcompose.util

// Some action that will represent some of the actions which we are going to trigger with our database table
// So we are going to use this Enum class to actually pass the action from our task composable to our listScreen
enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

// Taking a string and returning an enum entry
fun String?.toAction(): Action {
    // return if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
    return when {
        this == "ADD" -> {
            Action.ADD
        }
        this == "UPDATE" -> {
            Action.UPDATE
        }
        this == "DELETE" -> {
            Action.DELETE
        }
        this == "DELETE_ALL" -> {
            Action.DELETE_ALL
        }
        this == "UNDO" -> {
            Action.UNDO
        }
        else -> {
            Action.NO_ACTION
        }
    }
}
