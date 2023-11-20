package com.technical.task.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technical.task.core_android.activity.BaseActivity
import com.technical.task.ui.employeedetails.EmployeeDetailsViewModel
import com.technical.task.ui.employeedetails.model.EmployeeDetailsDestinationParam
import com.technical.task.ui.employeeslist.EmployeesListViewModel
import com.technical.task.ui.employeeslist.model.HomeScreenScreenParam
import com.technical.task.ui.navigation.DestinationKeys.EMPLOYEE_ID
import com.technical.task.ui.navigation.EmployeeDetailsDestination
import com.technical.task.ui.navigation.EmployeeListDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * @param modifier should be passed to all children, it contains innerPadding from Scaffold
     */
    @Composable
    override fun ScreenLayout(navHostController: NavHostController, modifier: Modifier) {

        NavHost(navController = navHostController, startDestination = EmployeeListDestination.route) {
            composable(EmployeeListDestination.route) {
                val viewModel = hiltViewModel<EmployeesListViewModel>()
                EmployeeListDestination.screen(
                    HomeScreenScreenParam(
                        modifier,
                        navHostController,
                        viewModel
                    )
                )
            }
            composable(EmployeeDetailsDestination.route + "/{" + EMPLOYEE_ID + "}") { backStackEntry ->
                val selectedEmployeeId = backStackEntry.arguments?.getString(EMPLOYEE_ID) ?: ""
                val viewModel = hiltViewModel<EmployeeDetailsViewModel>()
                EmployeeDetailsDestination.screen(
                    EmployeeDetailsDestinationParam(
                        selectedEmployeeId,
                        modifier,
                        viewModel
                    )
                )
            }
        }
    }
}
