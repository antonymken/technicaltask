package com.technical.task.ui.navigation

import androidx.compose.runtime.Composable
import com.technical.task.ui.employeedetails.EmployeeDetailsScreenManager
import com.technical.task.ui.employeeslist.EmployeesListScreenManager


interface ScreenParam

interface Destination {
    val route: String
    val screen: @Composable (ScreenParam) -> Unit
}

object EmployeeListDestination : Destination {
    override val route = Routes.EMPLOYEE_LIST

    override val screen: @Composable (screenParam: ScreenParam) -> Unit = { it -> EmployeesListScreenManager(it) }
}

object EmployeeDetailsDestination : Destination {
    override val route = Routes.EMPLOYEE_DETAILS

    override val screen: @Composable (screenParam: ScreenParam) -> Unit = { it -> EmployeeDetailsScreenManager(it) }
}

private object Routes {
    const val EMPLOYEE_LIST = "EMPLOYEE_LIST"
    const val EMPLOYEE_DETAILS = "EMPLOYEE_DETAILS"
}

object DestinationKeys {
    const val EMPLOYEE_ID = "EMPLOYEE_ID"
}