package com.technical.task.ui.employeedetails

import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.core_android.viewmodel.BaseViewModel
import com.technical.task.domain.directory.usecase.EmployeeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor(
    private val employeeDetailsUseCase: EmployeeDetailsUseCase
) : BaseViewModel<EmployeeDetailsState, EmployeeDetailsAction, EmployeeDetailsIntent>(EmployeeDetailsState.Default) {

    override fun actionToIntent(action: EmployeeDetailsAction): EmployeeDetailsIntent {
        return when (action) {
            is EmployeeDetailsAction.OnGetDetails -> EmployeeDetailsIntent.GetEmployeeDetails(action.id)
        }
    }

    override suspend fun processIntent(intent: EmployeeDetailsIntent) {
        when (intent) {
            is EmployeeDetailsIntent.GetEmployeeDetails -> getDetails(intent.id)
        }
    }

    private suspend fun getDetails(employeeId: String) {
        mutableStateFlow.emit(EmployeeDetailsState.Waiting)
        delay(300)//high value for demo
        employeeDetailsUseCase.execute(employeeId).collect { result ->
            when (result) {
                is Success ->
                    mutableStateFlow.emit(
                        EmployeeDetailsState.Success(result.data)
                    )

                is Error -> mutableStateFlow.emit(EmployeeDetailsState.Error(result.exception.message ?: "unknown error"))
            }
        }
    }
}