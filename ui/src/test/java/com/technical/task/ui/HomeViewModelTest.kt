package com.technical.task.ui

import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.usecase.GetNextToGoUseCase
import com.technical.task.ui.home.HomeAction
import com.technical.task.ui.home.HomeState
import com.technical.task.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var mockGetNextToGoUseCase: GetNextToGoUseCase

    @Mock
    lateinit var mockNextToGoRaceDomainModel1: NextToGoRaceDomainModel

    @Mock
    lateinit var mockNextToGoRaceDomainModel2: NextToGoRaceDomainModel

    @Mock
    lateinit var mockNextToGoRaceDomainModel3: NextToGoRaceDomainModel

    private val testDispatcher: TestDispatcher = (StandardTestDispatcher())

    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(mockGetNextToGoUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_loading_and_next_to_go_list_when_screen_loading_and_success() = runTest(testDispatcher) {
        Dispatchers.setMain(testDispatcher)
        val testResults = mutableListOf<HomeState>()
        val apiResult = listOf(
            mockNextToGoRaceDomainModel1,
            mockNextToGoRaceDomainModel2,
            mockNextToGoRaceDomainModel3
        )

        Mockito.`when`(mockGetNextToGoUseCase.execute(any())).thenReturn(
            flow {
                emit(
                    Success(apiResult)
                )
            }
        )

        val job = launch(testDispatcher) {
            homeViewModel.state.toList(testResults)
        }
        advanceUntilIdle()
        homeViewModel.handleAction(HomeAction.OnScreenLoading)
        advanceUntilIdle()

        assertEquals(3, testResults.size)
        assertEquals(HomeState.Default, testResults.first())
        assertEquals(HomeState.Waiting, testResults[1])
        assertEquals(HomeState.AllCategoriesList(apiResult), testResults.last())
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_loading_and_error_when_screen_loading_and_error() = runTest(testDispatcher) {
        Dispatchers.setMain(testDispatcher)
        val error = UnknownError("test error")
        val testResults = mutableListOf<HomeState>()

        Mockito.`when`(mockGetNextToGoUseCase.execute(any())).thenReturn(
            flow {
                emit(
                    Error(error)
                )
            }
        )

        val job = launch(testDispatcher) {
            homeViewModel.state.toList(testResults)
        }
        advanceUntilIdle()
        homeViewModel.handleAction(HomeAction.OnScreenLoading)
        advanceUntilIdle()

        assertEquals(3, testResults.size)
        assertEquals(HomeState.Default, testResults.first())
        assertEquals(HomeState.Waiting, testResults[1])
        assertEquals(HomeState.Error(error.message), testResults.last())
        job.cancel()
    }
}