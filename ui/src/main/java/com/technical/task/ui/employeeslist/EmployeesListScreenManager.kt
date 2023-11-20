package com.technical.task.ui.employeeslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.technical.task.ui.errorwaitscreen.ErrorScreen
import com.technical.task.ui.errorwaitscreen.WaitingScreen
import com.technical.task.ui.employeeslist.model.HomeScreenScreenParam
import com.technical.task.ui.navigation.ScreenParam

@Composable
fun EmployeesListScreenManager(screenParam: ScreenParam) {

    val params = screenParam as HomeScreenScreenParam
    val employeesListUiState by params.viewModel.state.collectAsState()

    when (employeesListUiState) {
        is EmployeesListState.Error -> ErrorScreen((employeesListUiState as EmployeesListState.Error).message, params.modifier)
        is EmployeesListState.Waiting -> WaitingScreen(params.modifier)
        is EmployeesListState.Success -> {
            EmployeesList(
                (employeesListUiState as EmployeesListState.Success).list,
                params.viewModel.isRefreshing,
                { params.viewModel.handleAction(EmployeesListAction.OnRefresh) },
                params.modifier,
                params.navHostController
            )
        }

        is EmployeesListState.Default -> {
            //no-op
        }
    }
}