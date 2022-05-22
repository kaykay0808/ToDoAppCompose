package com.kay.todoappcompose.data.models

import androidx.compose.ui.graphics.Color
import com.kay.todoappcompose.ui.theme.HighPriorityColor
import com.kay.todoappcompose.ui.theme.LowPriorityColor
import com.kay.todoappcompose.ui.theme.MediumPriorityColor
import com.kay.todoappcompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
