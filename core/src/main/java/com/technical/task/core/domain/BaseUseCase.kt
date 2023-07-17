package com.technical.task.core.domain

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


abstract class BaseUseCase<InputT, OutputT>(private val appCoroutineDispatchers: AppCoroutineDispatchers) {

    protected abstract suspend fun executeUseCase(requestValues: InputT): OutputT

    suspend fun execute(requestValues: InputT): OutputT {

        return withContext(appCoroutineDispatchers.io) {
            executeUseCase(requestValues)
        }
    }
}