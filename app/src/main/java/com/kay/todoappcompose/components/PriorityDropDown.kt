package com.kay.todoappcompose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // <- had to manually import this
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue // <- had to manually import this
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kay.todoappcompose.R
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.kay.todoappcompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    // the dropdown menu is not expanded my default?
    var expanded by remember { mutableStateOf(false) }

    // Icon arrow animation rotation state
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth() // fill out the row with the parent.
            .height(PRIORITY_DROP_DOWN_HEIGHT) // value created in Dimensions.kt
            .clickable { expanded = true } // when the row is clicked expanded value is true.
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
            ),
        verticalAlignment = Alignment.CenterVertically // Vannrett/center
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier
                .weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium) // make the icon button less visible
                .rotate(degrees = angle)
                .weight(weight = 1f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.drop_down_arrow)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // DropDown menu item 1
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW) // first dropdown menu item
                }

            ) {
                PriorityItem(priority = Priority.LOW)
            }
            // DropDown menu item 2
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM) // second dropdown menu item
                }

            ) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            // DropDown menu item 3
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH) // third dropdown menu item
                }

            ) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Composable
@Preview
fun PriorityDropDownPreview() {
    PriorityDropDown(
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}
