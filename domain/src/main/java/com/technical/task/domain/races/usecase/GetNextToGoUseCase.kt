package com.technical.task.domain.races.usecase

import com.technical.task.core.domain.BaseUseCase
import com.technical.task.core.domain.DataState
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.races.model.NextToGoDomainModel
import com.technical.task.domain.races.repository.RaceRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import javax.inject.Inject

class GetNextToGoUseCase @Inject constructor(
    private val raceRepository: RaceRepository
) : BaseUseCase<Unit, Flow<DataState<List<NextToGoDomainModel>>>>() {

    override suspend fun executeUseCase(requestValues: Unit): Flow<DataState<List<NextToGoDomainModel>>> {
        val flow = MutableSharedFlow<DataState<List<NextToGoDomainModel>>>()

        while (currentCoroutineContext().isActive) {
            try {
                val result = raceRepository.getNextToGo()
                flow.emit(
                    Success(
                        result
                    )
                )

            } catch (ex: Exception) {
                flow.emit(
                    Error(ex)
                )
            }
            delay(1000)
        }

        return flow
    }
}