package com.technical.task.ui.employeeslist

import com.technical.task.domain.directory.model.EmployeeDomainModel

sealed class EmployeesListState {
    data class Success(val list: List<EmployeeDomainModel>) : EmployeesListState()
    data class Error(val message: String) : EmployeesListState()
    object Waiting : EmployeesListState()
    object Default : EmployeesListState()
}