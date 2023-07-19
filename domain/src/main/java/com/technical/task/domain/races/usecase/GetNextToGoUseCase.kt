package com.technical.task.domain.races.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.BaseUseCase
import com.technical.task.core.domain.DataState
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.error.ApiException
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetNextToGoUseCase @Inject constructor(
    private val raceRepository: RaceRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : BaseUseCase<Unit, DataState<List<NextToGoRaceDomainModel>>>(appCoroutineDispatchers) {

    override suspend fun executeUseCase(requestValues: Unit): Flow<DataState<List<NextToGoRaceDomainModel>>> {
        val emitter: Flow<DataState<List<NextToGoRaceDomainModel>>> = flow {
            try {
                emit(
                    Success(raceRepository.getNextToGo(RECORD_COUNT))
                )
            } catch (ex: Exception) {
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

const val RECORD_COUNT = 5