package com.technical.task.domain.directory.repository

import com.technical.task.domain.directory.model.EmployeeDomainModel

interface EmployeeRepository {
    fun getEmployees(): List<EmployeeDomainModel>
    fun getEmployeeDetails(employeeId: String): EmployeeDomainModel
}