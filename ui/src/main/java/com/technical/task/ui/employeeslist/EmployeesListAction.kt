package com.technical.task.ui.employeeslist

sealed class EmployeesListAction {
    object OnScreenLoading : EmployeesListAction()
    object OnRefresh : EmployeesListAction()
}