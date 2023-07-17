package com.technical.task.domain.races.repository

import com.technical.task.domain.races.model.NextToGoDomainModel

interface RaceRepository {
    fun getNextToGo(): List<NextToGoDomainModel>
}