package com.technical.task.ui.employeeslist


import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.core_android.viewmodel.BaseViewModel
import com.technical.task.domain.directory.usecase.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase
) : BaseViewModel<EmployeesListState, EmployeesListAction, EmployeesListIntent>(EmployeesListState.Default) {

    private val mutableIsRefreshingFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = mutableIsRefreshingFlow

    init {
        handleAction(EmployeesListAction.OnScreenLoading)
    }

    override fun actionToIntent(action: EmployeesListAction): EmployeesListIntent {
        return when (action) {
            EmployeesListAction.OnScreenLoading -> EmployeesListIntent.GetEmployees
            EmployeesListAction.OnRefresh -> EmployeesListIntent.RefreshEmployees
        }
    }

    override suspend fun processIntent(intent: EmployeesListIntent) {
        when (intent) {
            EmployeesListIntent.GetEmployees -> loadEmployeesList()
            EmployeesListIntent.RefreshEmployees -> refreshEmployeesList()
        }
    }

    private suspend fun loadEmployeesList() {
        mutableStateFlow.emit(EmployeesListState.Waiting)
        delay(200)//high value for demo
        getEmployeesList()
    }

    private suspend fun refreshEmployeesList() {
        getEmployeesList()
    }

    private suspend fun getEmployeesList() {
        getEmployeesUseCase.execute(Unit).collect { result ->
            when (result) {
                is Success ->
                    mutableStateFlow.emit(
                        EmployeesListState.Success(
                            result.data.sortedBy { it.fullName }
                        )
                    )

                is Error -> mutableStateFlow.emit(EmployeesListState.Error(result.exception.message ?: "unknown error"))
            }
        }
    }
}
