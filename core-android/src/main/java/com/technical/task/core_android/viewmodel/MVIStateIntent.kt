package com.technical.task.core_android.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface MVIStateIntent<STATE, INTENT> {

    val state: StateFlow<STATE>

    suspend fun processIntent(intent: INTENT)
}