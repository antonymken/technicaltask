package com.technical.task.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.ui.BaseScreenTest
import com.technical.task.ui.home.screen.NEXT_TO_GO_LIST
import com.technical.task.ui.home.screen.NextToGoList
import org.junit.Test


class NextToGoListScreen : BaseScreenTest() {

    @Test
    fun should_show_list_when_screen_loaded() {

        composeTestRule.apply {
            setContent {
                NextToGoList(
                    listOf(
                        NextToGoRaceDomainModel(
                            "Traralgon",
                            9,
                            "4a2788f8-e825-4d36-9894-efd4baf1cfae",
                            1689582600
                        ),
                        NextToGoRaceDomainModel(
                            "Morioka",
                            11,
                            "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                            1689582960
                        ),
                        NextToGoRaceDomainModel(
                            "Richmond",
                            11,
                            "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                            1689582420
                        ),
                        NextToGoRaceDomainModel(
                            "Northam",
                            10,
                            "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                            1689583140
                        )
                    )
                )
            }
            composeTestRule.onNodeWithTag(NEXT_TO_GO_LIST).assertIsDisplayed()
        }
    }
}