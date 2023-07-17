package com.technical.task.data.races

import com.technical.task.data.races.model.NextToGoRaceData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RaceApi {

    companion object {
        const val NEXT_TO_GO_RACE_PATH = "/rest/v1/racing/{path}"
    }

    @Headers(
        value = ["Accept: application/json"]
    )
    @GET(NEXT_TO_GO_RACE_PATH)
    fun getNextToGo(@Path("path") path: String): Call<NextToGoRaceData>
}