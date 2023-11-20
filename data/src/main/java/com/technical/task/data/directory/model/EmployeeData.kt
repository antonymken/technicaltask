package com.technical.task.data.directory.model

import com.squareup.moshi.Json

class EmployeeData(
    @field:Json(name = "employees")
    var employees: List<Employee>
)

class Employee(
    @field:Json(name = "uuid")
    var uuid: String,
    @field:Json(name = "full_name")
    var fullName: String,
    @field:Json(name = "biography")
    var summary: String,
    @field:Json(name = "photo_url_small")
    var photoUrlSmall: String,
    @field:Json(name = "photo_url_large")
    var photoUrlLarge: String,
    @field:Json(name = "team")
    var team: String
)