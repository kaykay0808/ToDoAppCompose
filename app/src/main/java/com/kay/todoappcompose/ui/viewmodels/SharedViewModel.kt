package com.kay.todoappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.data.repository.ToDoRepository
import com.kay.todoappcompose.util.RequestState
import com.kay.todoappcompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
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
}
