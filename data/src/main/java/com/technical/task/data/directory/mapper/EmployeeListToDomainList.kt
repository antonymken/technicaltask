package com.technical.task.data.directory.mapper

import com.technical.task.core.mapper.BaseListMapper
import com.technical.task.data.directory.model.Employee
import com.technical.task.domain.directory.model.EmployeeDomainModel

object EmployeeListToDomainList :
    BaseListMapper<Employee, EmployeeDomainModel>() {
    override fun transform(model: Employee): EmployeeDomainModel {
        return EmployeeToDomain.transform(model)
    }
}