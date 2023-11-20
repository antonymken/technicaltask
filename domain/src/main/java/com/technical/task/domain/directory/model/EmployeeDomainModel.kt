package com.technical.task.domain.directory.model

data class EmployeeDomainModel(
    val uuid :String,
    val fullName: String,
    val summary: String,
    val photoUrlSmall: String,
    val photoUrlLarge: String,
    val team: String
)