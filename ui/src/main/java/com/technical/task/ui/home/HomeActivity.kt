package com.technical.task.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.technical.task.core_android.activity.BaseActivity
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.handleAction(HomeAction.OnScreenLoading)
    }

    @Composable
    override fun SetAppLayout(navHostController: NavHostController) {
        val uiState by viewModel.state.collectAsState()
        LoadScreen(uiState)
    }

    @Composable
    fun LoadScreen(homeState: HomeState) {

        when (homeState) {
            is HomeState.ApiError -> ErrorScreen()
            is HomeState.CheckingRaces -> WaitingScreen()

            is HomeState.AllCategoriesList -> NextToGoList(homeState.list)
            is HomeState.Default -> {
                //no-op
            }
        }
    }

    @Composable
    fun NextToGoList(list: List<NextToGoRaceDomainModel>) {

    }

    @Composable
    fun ErrorScreen() {

    }

    @Composable
    fun WaitingScreen() {

    }
}
