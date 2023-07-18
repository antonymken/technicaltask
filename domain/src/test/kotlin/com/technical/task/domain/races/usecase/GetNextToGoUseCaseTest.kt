package com.technical.task.domain.races.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.repository.RaceRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetNextToGoUseCaseTest {

    @Mock
    lateinit var mockRaceRepository: RaceRepository

    @Mock
    lateinit var mockAppCoroutineDispatchers: AppCoroutineDispatchers

    @Mock
    lateinit var mockNextToGoRaceDomainModel: NextToGoRaceDomainModel

    @Mock
    lateinit var mockNextToGoRaceDomainModel1: NextToGoRaceDomainModel

    @Mock
    lateinit var mockNextToGoRaceDomainModel2: NextToGoRaceDomainModel

    private val testDispatcher: TestDispatcher = (StandardTestDispatcher())

    private lateinit var getNextToGoUseCase: GetNextToGoUseCase

    @Before
    fun setUp() {
        getNextToGoUseCase = GetNextToGoUseCase(
            mockRaceRepository,
            mockAppCoroutineDispatchers
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_list_on_execute() = runTest(testDispatcher) {

        Mockito.`when`(mockAppCoroutineDispatchers.io).thenReturn(testDispatcher)
        Mockito.`when`(mockRaceRepository.getNextToGo(anyInt())).thenReturn(
            listOf(
                mockNextToGoRaceDomainModel,
                mockNextToGoRaceDomainModel1,
                mockNextToGoRaceDomainModel2
            )
        )

        getNextToGoUseCase.execute(Unit).collect { data ->
            assertEquals(mockNextToGoRaceDomainModel1, (data as Success<List<NextToGoRaceDomainModel>>).data[1])
        }

        advanceUntilIdle()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_error_on_execute_and_error() = runTest(testDispatcher) {

        val errorMessage = "Test Error"
        Mockito.`when`(mockAppCoroutineDispatchers.io).thenReturn(testDispatcher)
        Mockito.`when`(mockRaceRepository.getNextToGo(anyInt())).thenThrow(UnknownError(errorMessage));

        getNextToGoUseCase.execute(Unit).collect { data ->
            assertEquals(errorMessage, (data as Error).exception.message)
        }

        advanceUntilIdle()

    }

}