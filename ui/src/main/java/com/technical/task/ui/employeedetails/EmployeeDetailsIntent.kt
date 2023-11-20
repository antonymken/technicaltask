package com.technical.task.ui.employeedetails

sealed class EmployeeDetailsIntent {
    data class GetEmployeeDetails(val id: String) : EmployeeDetailsIntent()
}