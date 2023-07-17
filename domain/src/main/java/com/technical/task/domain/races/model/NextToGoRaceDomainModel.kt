package com.technical.task.domain.races.model

import java.util.Date

data class NextToGoRaceDomainModel(
    val meetingName: String,
    val raceNumber: Int,
    val category: String,
    val startTime: Long
)