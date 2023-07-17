package com.technical.task.domain.races.model

import sun.util.calendar.BaseCalendar.Date

data class NextToGoDomainModel(
    val meetingName:String,
    val raceNumber:String,
    val category:String,
    val startTime: Date
)