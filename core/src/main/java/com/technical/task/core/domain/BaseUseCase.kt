package com.technical.task.core.domain

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


abstract class BaseUseCase<InputT, OutputT>(private val appCoroutineDispatchers: AppCoroutineDispatchers) {

    protected abstract suspend fun executeUseCase(requestValues: InputT): Flow<OutputT>

    suspend fun execute(requestValues: InputT): Flow<OutputT> {
        return executeUseCase(requestValues).flowOn(appCoroutineDispatchers.io)
    }
}