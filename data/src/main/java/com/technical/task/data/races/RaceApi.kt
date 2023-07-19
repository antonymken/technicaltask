package com.technical.task.data.races

import com.technical.task.data.races.model.PayLoad
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RaceApi {

    companion object {
        const val NEXT_TO_GO_RACE_PATH = "/rest/v1/racing/{query}"
    }

    @Headers(
        value = ["Accept: application/json"]
    )
    @GET(NEXT_TO_GO_RACE_PATH)
    fun getNextToGo(
        @Query("method") method: String,
        @Query("count") count: Int
    ): Call<PayLoad>
}