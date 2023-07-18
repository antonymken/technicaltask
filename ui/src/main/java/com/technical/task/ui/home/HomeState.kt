package com.technical.task.ui.home

import com.technical.task.domain.races.model.NextToGoRaceDomainModel

sealed class HomeState {
    data class AllCategoriesList(val list: List<NextToGoRaceDomainModel>) : HomeState()
    data class ApiError(val message: String? = "unknown error") : HomeState()
    object CheckingRaces : HomeState()
    object Default : HomeState()
}