package com.technical.task.ui.employeedetails.model

import androidx.compose.ui.Modifier
import com.technical.task.ui.employeedetails.EmployeeDetailsViewModel
import com.technical.task.ui.navigation.ScreenParam

data class EmployeeDetailsDestinationParam(
    val selectedEmployeeId: String,
    val modifier: Modifier,
    val viewModel: EmployeeDetailsViewModel
) : ScreenParam
