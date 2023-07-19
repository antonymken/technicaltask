package com.technical.task.ui.home


import com.technical.task.core.domain.Error
import com.technical.task.core.domain.Success
import com.technical.task.core_android.viewmodel.BaseViewModel
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.usecase.GetNextToGoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNextToGoUseCase: GetNextToGoUseCase
) : BaseViewModel<HomeState, HomeAction, HomeIntent>(HomeState.Default) {

    override fun actionToIntent(action: HomeAction): HomeIntent {
        return when (action) {
            HomeAction.OnScreenLoading -> HomeIntent.GetNextToGo
        }
    }

    override suspend fun processIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.GetNextToGo -> loadNextToGo()
        }
    }

    private suspend fun loadNextToGo() {
        mutableStateFlow.emit(HomeState.CheckingRaces)
        delay(500)
        getNextToGoUseCase.execute(Unit).collect { result ->
            when (result) {
                is Success<*> ->
                    mutableStateFlow.emit(
                        HomeState.AllCategoriesList(
                            result.data as List<NextToGoRaceDomainModel>
                        )
                    )
                is Error -> mutableStateFlow.emit(HomeState.ApiError(result.exception.message))
            }
        }
    }
}
