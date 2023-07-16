package com.technical.task.core_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, ACTION, INTENT>(defaultState: STATE) : MVIStateIntent<STATE, INTENT>, ViewModel() {

    protected val mutableStateFlow: MutableStateFlow<STATE> = MutableStateFlow(defaultState)

    override val state: StateFlow<STATE>
        get() = mutableStateFlow

    fun handleAction(action: ACTION) {//action from the user
        viewModelScope.launch {
            processIntent(actionToIntent(action))
        }
    }

    protected abstract fun actionToIntent(action: ACTION): INTENT
}