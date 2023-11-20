package com.technical.task.data.directory.mapper

import com.technical.task.core.mapper.InterfaceItemMapper
import com.technical.task.data.directory.model.Employee
import com.technical.task.domain.directory.model.EmployeeDomainModel

object EmployeeToDomain :
    InterfaceItemMapper<Employee, EmployeeDomainModel> {
    override fun transform(model: Employee): EmployeeDomainModel {
        return EmployeeDomainModel(
            uuid = model.uuid,
            fullName = model.fullName,
            summary = model.summary,
            photoUrlSmall = model.photoUrlSmall,
            photoUrlLarge = model.photoUrlLarge,
            team = model.team
        )
    }
}