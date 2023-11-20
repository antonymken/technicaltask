package com.technical.task.ui.employeeslist.model

import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.technical.task.ui.employeeslist.EmployeesListViewModel
import com.technical.task.ui.navigation.ScreenParam

data class HomeScreenScreenParam(
    val modifier: Modifier,
    val navHostController: NavHostController,
    val viewModel: EmployeesListViewModel
) : ScreenParam