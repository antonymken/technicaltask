package com.technical.task.domain.races.repository

import com.technical.task.domain.races.model.NextToGoRaceDomainModel

interface RaceRepository {
    fun getNextToGo(itemCount: Int): List<NextToGoRaceDomainModel>
}