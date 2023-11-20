package com.technical.task.ui.employeedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.technical.task.ui.employeedetails.model.EmployeeDetailsDestinationParam
import com.technical.task.ui.errorwaitscreen.ErrorScreen
import com.technical.task.ui.errorwaitscreen.WaitingScreen
import com.technical.task.ui.navigation.ScreenParam


const val EMPLOYEES_DETAILS = "EMPLOYEES_DETAILS"

@Composable
fun EmployeeDetailsScreenManager(destinationParam: ScreenParam) {

    val param = destinationParam as EmployeeDetailsDestinationParam
    val currentEmployeeId = remember { mutableStateOf("") }
    val employeeDetailsUiState by param.viewModel.state.collectAsState()

    if (currentEmployeeId.value != param.selectedEmployeeId) {//only get data if different id selected
        param.viewModel.handleAction(
            EmployeeDetailsAction.OnGetDetails(
                param.selectedEmployeeId
            )
        )
        currentEmployeeId.value = param.selectedEmployeeId
    }

    when (employeeDetailsUiState) {
        EmployeeDetailsState.Default -> {}
        is EmployeeDetailsState.Error -> ErrorScreen((employeeDetailsUiState as EmployeeDetailsState.Error).message, param.modifier)
        is EmployeeDetailsState.Success -> EmployeeDetailsScreen(
            EmployeeDetailsScreenParams(param.modifier, (employeeDetailsUiState as EmployeeDetailsState.Success).employeeDetails)
        )

        EmployeeDetailsState.Waiting -> WaitingScreen(param.modifier)
    }

}

