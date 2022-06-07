package com.kay.todoappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kay.todoappcompose.data.models.ToDoTask
import com.kay.todoappcompose.data.repository.ToDoRepository
import com.kay.todoappcompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    // Define states which we are to observe from our list
    val searchAppBarState: MutableState<SearchAppBarState> =
        // Set the MutableState<> to a default value below
        mutableStateOf(SearchAppBarState.CLOSED)

    // The value of the variable below will be used to set the text of our text field.
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTask = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTask: StateFlow<List<ToDoTask>> = _allTask

    fun getAllTask() {
        viewModelScope.launch {
            repository.getAllTask.collect {
                _allTask.value = it
            }
        }
    }
}
