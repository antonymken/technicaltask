package com.technical.task.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class BaseScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

}