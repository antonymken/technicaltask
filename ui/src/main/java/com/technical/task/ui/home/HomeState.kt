package com.technical.task.ui.home

import com.technical.task.domain.races.model.NextToGoRaceDomainModel

sealed class HomeState {
    data class AllCategoriesList(val list: List<NextToGoRaceDomainModel>) : HomeState()
    data class Error(val message: String) : HomeState()
    object Waiting : HomeState()
    object Default : HomeState()
}