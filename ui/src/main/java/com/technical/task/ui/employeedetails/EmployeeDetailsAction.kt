package com.technical.task.ui.employeedetails

sealed class EmployeeDetailsAction {
    data class OnGetDetails(val id: String) : EmployeeDetailsAction()
}