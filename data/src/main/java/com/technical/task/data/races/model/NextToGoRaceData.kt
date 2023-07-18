package com.technical.task.data.races.model

import com.squareup.moshi.Json

class PayLoad(
    @field:Json(name = "data")
    var data: NextToGoRaceData
)

class NextToGoRaceData(
    @field:Json(name = "race_summaries")
    var races: Map<String, RaceSummary>
)

class RaceSummary(
    @field:Json(name = "category_id")
    var category: String,
    @field:Json(name = "meeting_name")
    var meetingName: String,
    @field:Json(name = "race_number")
    var raceNumber: Int,
    @field:Json(name = "advertised_start")
    var startTime: AdvertisedStart,
)

class AdvertisedStart(
    @field:Json(name = "seconds")
    var seconds: Long
)