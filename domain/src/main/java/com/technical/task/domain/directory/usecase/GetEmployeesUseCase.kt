package com.technical.task.domain.directory.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.BaseUseCase
import com.technical.task.core.domain.DataState
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.error.ApiException
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.domain.directory.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val EmployeeRepository: EmployeeRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : BaseUseCase<Unit, DataState<List<EmployeeDomainModel>>>(appCoroutineDispatchers) {

    override suspend fun executeUseCase(requestValues: Unit): Flow<DataState<List<EmployeeDomainModel>>> {
        val emitter: Flow<DataState<List<EmployeeDomainModel>>> = flow {
            try {
                emit(
                    Success(EmployeeRepository.getEmployees())
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