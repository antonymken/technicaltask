package com.technical.task.data.directory

import com.technical.task.data.directory.model.EmployeeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface EmployeeApi {

    companion object {
        const val EMPLOYEE_LIST_PATH = "/sq-mobile-interview/employees.json"
    }

    @Headers(
        value = ["Accept: application/json"]
    )
    @GET(EMPLOYEE_LIST_PATH)
    fun getEmployees(): Call<EmployeeData>
}