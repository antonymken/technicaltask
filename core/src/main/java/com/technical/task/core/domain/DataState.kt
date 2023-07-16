package com.technical.task.core.domain


sealed class DataState<out T>

data class Success<T>(val data: T) : DataState<T>()

data class Error(val exception: Throwable) : DataState<Nothing>()

object Loading : DataState<Nothing>()