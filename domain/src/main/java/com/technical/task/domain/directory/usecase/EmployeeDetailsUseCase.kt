package com.technical.task.domain.directory.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.BaseUseCase
import com.technical.task.core.domain.DataState
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.domain.directory.repository.EmployeeRepository
import com.technical.task.domain.error.ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmployeeDetailsUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : BaseUseCase<String, DataState<EmployeeDomainModel>>(appCoroutineDispatchers) {

    override suspend fun executeUseCase(requestValues: String): Flow<DataState<EmployeeDomainModel>> {
        val emitter: Flow<DataState<EmployeeDomainModel>> = flow {
            try {
                emit(
                    Success(employeeRepository.getEmployeeDetails(requestValues))
                )
            } catch (ex: Throwable) {
                if (ex is ApiException) {
                    emit(
                        Error(ex)
                    )
                } else {
                    emit(
                        Error(ApiException("something went wrong"))
                    )
                }
            }
        }
        return emitter
    }
}