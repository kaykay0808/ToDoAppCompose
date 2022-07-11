package com.kay.todoappcompose.util

// (fixing the noContentView displaying for a second)
// 4  Different state
// (1) Idle
// (2) Loading
// (3) success
// (4) Error
sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}
