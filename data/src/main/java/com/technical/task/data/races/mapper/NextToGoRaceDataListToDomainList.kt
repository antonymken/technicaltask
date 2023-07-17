package com.technical.task.data.races.mapper

import com.technical.task.core.mapper.BaseListMapper
import com.technical.task.data.races.model.RaceSummary
import com.technical.task.domain.races.model.NextToGoRaceDomainModel

object NextToGoRaceDataListToDomainList :
    BaseListMapper<RaceSummary, NextToGoRaceDomainModel>() {
    override fun transform(model: RaceSummary): NextToGoRaceDomainModel {
        return NextToGoRaceDataToDomain.transform(model)
    }
}