package com.kay.todoappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kay.todoappcompose.data.models.Priority
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.data.repository.ToDoRepository
import com.kay.todoappcompose.util.Action
import com.kay.todoappcompose.util.Constants.MAX_TITLE_LENGTH
import com.kay.todoappcompose.util.RequestState
import com.kay.todoappcompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {
    /** ------------------------- DATABASE -----------------------------------------*/

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)


    // Save some taskScreen values
    val id: MutableState<Int> = mutableStateOf(0) // Default value for our Id will be 0
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)


    // Define states which we are to observe from our list
    val searchAppBarState: MutableState<SearchAppBarState> =
        // Set the MutableState<> to a default value below
        mutableStateOf(SearchAppBarState.CLOSED)

    // The value of the variable below will be used to set the text of our text field.
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTask: StateFlow<RequestState<List<ToDoTask>>> = _allTask

    fun getAllTask() {
        _allTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTask.collect {
                    _allTask.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTask.value = RequestState.Error(e)
        }
    }

    /** -------------------- Operations for reading our database -------------------------------------- */
    private val _selectedTask: MutableStateFlow<ToDoTask?> =
        MutableStateFlow(null)

    /* This selectedTask variable will automatically get the value,
    * and get observed from our task screen */
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        /* Whenever we select this get selected task function,
        we will get the same taskId which we are passed into the function from the database,
        and that task should be set in into _selectedTask
        .*/
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    // Function that updating our mutableState values (Title, description, priority)
    fun updateTaskField(selectedTask: ToDoTask?) {
        // Check if selectedTask is null (if we have clicked on the specific task)
        if (selectedTask != null) {
            // set the values of each variable from our mutableState
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority

            // Default values
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    /** -------------------- Operation for updating our data ------------------------------------ */

    fun updateTitle(newTitle: String) {
        // if new title is less then max title length
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }  
    }
    /** ----------------------Adding Data------------------------*/
    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask = toDoTask)
        }
    }


    /** ----------- Validation ------------- */

    fun validateFields(): Boolean {
        // if both field is NOT empty then we are going to return "true"
        return title.value.isNotEmpty() && description.value.isNotEmpty()

    }

    /** ------------------ ACTIONS -------------------*/

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {
                // todo: creating an update function
            }
            Action.DELETE -> {
                // todo: creating a delete function
            }
            Action.DELETE_ALL -> {
                // todo: Creating a delete all function
            }
            Action.UNDO -> {
                // todo: Creating an undo function
            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }
}
