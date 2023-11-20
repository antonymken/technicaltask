package com.technical.task.data.directory

import com.technical.task.data.RetrofitClient
import com.technical.task.data.directory.di.EmployeeApiUrl
import com.technical.task.data.directory.mapper.EmployeeListToDomainList
import com.technical.task.domain.error.ApiException
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.domain.directory.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeRepositoryImp @Inject constructor(
    @EmployeeApiUrl private val url: String
) : EmployeeRepository, RetrofitClient() {

    override val baseUrl: String = url
    private val employeeApi = createApiClient<EmployeeApi>()

    override fun getEmployees(): List<EmployeeDomainModel> {
        val result = employeeApi.getEmployees().execute()
        if (result.errorBody() != null) {
            throw ApiException("server error")
        }
        return EmployeeListToDomainList.transformList(
            result.body()?.employees
        ).toList()
    }

    override fun getEmployeeDetails(uuid: String): EmployeeDomainModel {
        return getEmployees().first { it.uuid == uuid }
    }
}