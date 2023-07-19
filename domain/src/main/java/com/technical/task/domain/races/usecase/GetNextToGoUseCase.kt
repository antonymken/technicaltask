package com.technical.task.domain.races.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.BaseUseCase
import com.technical.task.core.domain.DataState
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.races.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNextToGoUseCase @Inject constructor(
    private val raceRepository: RaceRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : BaseUseCase<Unit, DataState>(appCoroutineDispatchers) {

    override suspend fun executeUseCase(requestValues: Unit): Flow<DataState> {
        val emitter: Flow<DataState> = flow {
            emit(
                Success(raceRepository.getNextToGo(RECORD_COUNT)) as DataState
            )
        }.catch { ex ->
            emit(
                Error(ex)
            )
        }
        return emitter
    }
}

const val RECORD_COUNT = 5