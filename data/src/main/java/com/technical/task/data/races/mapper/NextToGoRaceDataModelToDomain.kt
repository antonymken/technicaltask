package com.technical.task.data.races.mapper

import com.technical.task.core.mapper.InterfaceItemMapper
import com.technical.task.data.races.model.RaceSummary
import com.technical.task.domain.races.model.NextToGoRaceDomainModel

object NextToGoRaceDataToDomain :
    InterfaceItemMapper<RaceSummary, NextToGoRaceDomainModel> {
    override fun transform(model: RaceSummary): NextToGoRaceDomainModel {
        return NextToGoRaceDomainModel(
            meetingName = model.meetingName,
            raceNumber = model.raceNumber,
            category = model.category,
            advertisedStartTime = model.startTime.seconds
        )
    }
}