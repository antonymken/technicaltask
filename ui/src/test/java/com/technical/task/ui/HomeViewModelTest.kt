package com.technical.task.ui

import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.domain.directory.usecase.GetEmployeesUseCase
import com.technical.task.domain.error.ApiException
import com.technical.task.ui.employeeslist.EmployeesListState
import com.technical.task.ui.employeeslist.EmployeesListViewModel
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
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var mockGetEmployeesUseCase: GetEmployeesUseCase

    @Mock
    lateinit var mockEmployeeDomainModel: EmployeeDomainModel
    @Mock
    lateinit var mockEmployeeDomainModel1: EmployeeDomainModel
    @Mock
    lateinit var mockEmployeeDomainModel2: EmployeeDomainModel

    private val testDispatcher: TestDispatcher = (StandardTestDispatcher())

    lateinit var homeViewModel: EmployeesListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_loading_and_employees_list_when_screen_loading_and_success() = runTest(testDispatcher) {
        Dispatchers.setMain(testDispatcher)
        val testResults = mutableListOf<EmployeesListState>()
        val apiResult = listOf(
            mockEmployeeDomainModel,
            mockEmployeeDomainModel1,
            mockEmployeeDomainModel2
        )

        Mockito.`when`(mockGetEmployeesUseCase.execute(any())).thenReturn(
            flow {
                emit(
                    Success(apiResult)
                )
            }
        )

        val job = launch(testDispatcher) {
            homeViewModel = EmployeesListViewModel(mockGetEmployeesUseCase)
            homeViewModel.state.toList(testResults)
        }
        advanceUntilIdle()

        assertEquals(3, testResults.size)
        assertEquals(EmployeesListState.Default, testResults.first())
        assertEquals(EmployeesListState.Waiting, testResults[1])
        assertEquals(EmployeesListState.Success(apiResult), testResults.last())
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_loading_and_error_when_screen_loading_and_error() = runTest(testDispatcher) {
        Dispatchers.setMain(testDispatcher)
        val error = ApiException("test error")
        val testResults = mutableListOf<EmployeesListState>()

        Mockito.`when`(mockGetEmployeesUseCase.execute(any())).thenReturn(
            flow {
                emit(
                    Error(error)
                )
            }
        )

        val job = launch(testDispatcher) {
            homeViewModel = EmployeesListViewModel(mockGetEmployeesUseCase)
            homeViewModel.state.toList(testResults)
        }
        advanceUntilIdle()

        assertEquals(3, testResults.size)
        assertEquals(EmployeesListState.Default, testResults.first())
        assertEquals(EmployeesListState.Waiting, testResults[1])
        assertEquals(EmployeesListState.Error(error.message!!), testResults.last())
        job.cancel()
    }
}