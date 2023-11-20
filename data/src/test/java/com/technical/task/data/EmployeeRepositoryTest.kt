package com.technical.task.data

import com.technical.task.data.directory.EmployeeRepositoryImp
import com.technical.task.domain.directory.repository.EmployeeRepository
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class EmployeeRepositoryTest {
    private var mockWebServer = MockWebServer()
    lateinit var employeeRepository: EmployeeRepository

    @Before
    fun setUp() {
        employeeRepository = EmployeeRepositoryImp(mockWebServer.url("/").toString())
    }

    @Test
    fun should_return_list_when_getEmployees() {
        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(employeeListTestPayload())

        mockWebServer.enqueue(response)

        val data = employeeRepository.getEmployees()
        assertEquals("Richard Stein", data[2].fullName)
        assertEquals("Product manager for the Point of sale app!", data[2].summary)
        assertEquals("https://s3.amazonaws.com/sq-mobile-interview/photos/43ed39b3-fbc0-4eb8-8ed3-6a8de479a52a/small.jpg", data[2].photoUrlSmall)
        assertEquals("Point of Sale", data[2].team)
    }

    private fun employeeListTestPayload(): String {
        return "{\"employees\": [{\"biography\": \"Engineer on the Point of Sale team.\", \"email_address\": \"jmason.demo@squareup.com\", \"employee_type\": \"FULL_TIME\", \"full_name\": \"Justine Mason\", \"phone_number\": \"5553280123\", \"photo_url_large\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg\", \"photo_url_small\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg\", \"team\": \"Point of Sale\", \"uuid\": \"0d8fcc12-4d0c-425c-8355-390b312b909c\"}, {\"biography\": \"Designer on the web marketing team.\", \"email_address\": \"crogers.demo@squareup.com\", \"employee_type\": \"PART_TIME\", \"full_name\": \"Camille Rogers\", \"phone_number\": \"5558531970\", \"photo_url_large\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/large.jpg\", \"photo_url_small\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/small.jpg\", \"team\": \"Public Web & Marketing\", \"uuid\": \"a98f8a2e-c975-4ba3-8b35-01f719e7de2d\"}, {\"biography\": \"Product manager for the Point of sale app!\", \"email_address\": \"rstein.demo@squareup.com\", \"employee_type\": \"PART_TIME\", \"full_name\": \"Richard Stein\", \"phone_number\": \"5557223332\", \"photo_url_large\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/43ed39b3-fbc0-4eb8-8ed3-6a8de479a52a/large.jpg\", \"photo_url_small\": \"https://s3.amazonaws.com/sq-mobile-interview/photos/43ed39b3-fbc0-4eb8-8ed3-6a8de479a52a/small.jpg\", \"team\": \"Point of Sale\", \"uuid\": \"b8cf3382-ecf2-4240-b8ab-007688426e8c\"}]}"
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}