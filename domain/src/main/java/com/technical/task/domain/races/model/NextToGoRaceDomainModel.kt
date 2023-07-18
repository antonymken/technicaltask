package com.technical.task.domain.races.model

data class NextToGoRaceDomainModel(
    val meetingName: String,
    val raceNumber: Int,
    val category: String,
    val advertisedStartTime: Long
)