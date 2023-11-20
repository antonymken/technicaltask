package com.technical.task.ui.employeeslist

sealed class EmployeesListIntent {
    object GetEmployees : EmployeesListIntent()
    object RefreshEmployees : EmployeesListIntent()
}