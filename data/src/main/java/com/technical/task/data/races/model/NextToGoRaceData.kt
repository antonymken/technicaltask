package com.technical.task.data.races.model

import com.squareup.moshi.Json

class NextToGoRaceData(
    @Json(name = "race_summaries")
    var races: MutableList<RaceSummary>
)

class RaceSummary(
    @Json(name = "category_id")
    var category: String,
    @Json(name = "meeting_name")
    var meetingName: String,
    @Json(name = "race_number")
    var raceNumber: Int,
    @Json(name = "advertised_start")
    var startTime: Long,
)