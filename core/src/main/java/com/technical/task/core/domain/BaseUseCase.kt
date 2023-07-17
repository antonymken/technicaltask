package com.technical.task.core.domain

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


abstract class BaseUseCase<InputT, OutputT>() {

    @Inject
    lateinit var appCoroutineDispatchers: AppCoroutineDispatchers

    protected abstract suspend fun executeUseCase(requestValues: InputT): OutputT

    suspend fun execute(requestValues: InputT): OutputT {
        lateinit var deferred: Deferred<OutputT>
        withContext(appCoroutineDispatchers.io) {
            deferred = async {
                executeUseCase(requestValues)
            }
        }
        return deferred.await()
    }
}