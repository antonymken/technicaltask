package com.technical.task.core_android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.technical.task.app.R
import com.technical.task.core_android.annotation.LightDarkPreview
import com.technical.task.core_android.theme.AppTheme
import com.technical.task.core_android.theme.typography
import dagger.hilt.android.AndroidEntryPoint

/**
 * **  Base activity sets up hilt injection
 */
@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BaseScreen() {
        val navController = rememberNavController()
        AppTheme {
            Surface(
                tonalElevation = 5.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorScheme.background)
            ) {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            modifier = Modifier
                                .height(100.dp),
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    color = colorScheme.onPrimary,
                                    style = typography.headlineMedium,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorScheme.onPrimaryContainer,
                                titleContentColor = colorScheme.onPrimary
                            ),
                        )
                    },
                    content = { innerPadding ->
                        ScreenLayout(navHostController = navController, Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }

    @Composable
    abstract fun ScreenLayout(navHostController: NavHostController, modifier: Modifier)

    @LightDarkPreview
    @Composable
    fun TechnicalTaskAppPreview() {
        BaseScreen()
    }
}