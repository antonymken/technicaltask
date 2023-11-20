package com.technical.task.ui.employeedetails

import com.technical.task.domain.directory.model.EmployeeDomainModel

sealed class EmployeeDetailsState {
    data class Success(val employeeDetails: EmployeeDomainModel) : EmployeeDetailsState()
    data class Error(val message: String) : EmployeeDetailsState()
    object Waiting : EmployeeDetailsState()
    object Default : EmployeeDetailsState()
}