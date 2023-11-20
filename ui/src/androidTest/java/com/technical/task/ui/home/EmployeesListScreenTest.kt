package com.technical.task.ui.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.ui.BaseScreenTest
import com.technical.task.ui.employeeslist.EMPLOYEES_LIST
import com.technical.task.ui.employeeslist.EmployeesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Test


class EmployeesListScreenTest : BaseScreenTest() {

    @Test
    fun should_show_list_when_screen_loaded() {
        val isRefreshing: StateFlow<Boolean> = MutableStateFlow(false)
        composeTestRule.apply {
            setContent {
                EmployeesList(
                    listOf(
                        EmployeeDomainModel(
                            "uuid1",
                            "Justine Mason",
                            "Engineer on the Point of Sale team.",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                            "Point of Sale"
                        ),
                        EmployeeDomainModel(
                            "uuid2",
                            "Camille Rogers",
                            "Designer on the web marketing team.",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/small.jpg",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/small.jpg",
                            "Public Web & Marketing"
                        ),
                        EmployeeDomainModel(
                            "uuid3",
                            "Richard Stein",
                            "Product manager for the Point of sale app!",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/43ed39b3-fbc0-4eb8-8ed3-6a8de479a52a/small.jpg",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/43ed39b3-fbc0-4eb8-8ed3-6a8de479a52a/small.jpg",
                            "Point of Sale"
                        ),
                        EmployeeDomainModel(
                            "uuid4",
                            "Alaina Daly",
                            "Product marketing manager for the Retail Point of Sale app in New York.",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/57bc7ed2-5f9e-4814-a7df-dea85c2ed97f/small.jpg",
                            "https://s3.amazonaws.com/sq-mobile-interview/photos/57bc7ed2-5f9e-4814-a7df-dea85c2ed97f/small.jpg",
                            "Retail"
                        )
                    ),
                    isRefreshing,
                    {},
                    Modifier,
                    navController
                )
            }
            composeTestRule.onNodeWithTag(EMPLOYEES_LIST).assertIsDisplayed()
        }
    }
}