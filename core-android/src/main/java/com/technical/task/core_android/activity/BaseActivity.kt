package com.technical.task.core_android.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.technical.task.core_android.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * **  Base activity sets up hilt injection
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                    SetAppLayout(navHostController = navController)
                }
            }
        }
    }

    @Composable
    abstract fun SetAppLayout(navHostController: NavHostController)

    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        name = "DefaultPreviewDark"
    )
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_NO,
        name = "DefaultPreviewLight"
    )
    @Composable
    fun TechnicalTaskAppPreview() {
        val navController = rememberNavController()
        AppTheme {
            Surface(tonalElevation = 5.dp) {
                SetAppLayout(navHostController = navController)
            }
        }
    }
}