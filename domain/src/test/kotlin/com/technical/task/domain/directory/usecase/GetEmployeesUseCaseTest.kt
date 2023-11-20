package com.technical.task.domain.directory.usecase

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.domain.directory.repository.EmployeeRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetEmployeesUseCaseTest {

    @Mock
    lateinit var mockEmployeeRepository: EmployeeRepository

    @Mock
    lateinit var mockAppCoroutineDispatchers: AppCoroutineDispatchers

    @Mock
    lateinit var mockEmployeeDomainModel: EmployeeDomainModel
    @Mock
    lateinit var mockEmployeeDomainModel1: EmployeeDomainModel
    @Mock
    lateinit var mockEmployeeDomainModel2: EmployeeDomainModel

    private val testDispatcher: TestDispatcher = (StandardTestDispatcher())

    private lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Before
    fun setUp() {
        getEmployeesUseCase = GetEmployeesUseCase(
            mockEmployeeRepository,
            mockAppCoroutineDispatchers
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_list_on_execute() = runTest(testDispatcher) {

        Mockito.`when`(mockAppCoroutineDispatchers.io).thenReturn(testDispatcher)
        Mockito.`when`(mockEmployeeRepository.getEmployees()).thenReturn(
            listOf(
                mockEmployeeDomainModel,
                mockEmployeeDomainModel1,
                mockEmployeeDomainModel2
            )
        )

        getEmployeesUseCase.execute(Unit).collect { data ->
            assertEquals(mockEmployeeDomainModel1, (data as Success<List<EmployeeDomainModel>>).data[1])
        }

        advanceUntilIdle()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_emit_error_on_execute_and_error() = runTest(testDispatcher) {

        val errorMessage = "Test Error"
        Mockito.`when`(mockAppCoroutineDispatchers.io).thenReturn(testDispatcher)
        Mockito.`when`(mockEmployeeRepository.getEmployees()).thenThrow(UnknownError(errorMessage));

        getEmployeesUseCase.execute(Unit).collect { data ->
            assertEquals("something went wrong", (data as Error).exception.message)
        }

        advanceUntilIdle()

    }

}