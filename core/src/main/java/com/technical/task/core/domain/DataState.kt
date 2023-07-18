package com.technical.task.core.domain


sealed class DataState

data class Success<T>(val data: T) : DataState()

data class Error(val exception: Throwable) : DataState()