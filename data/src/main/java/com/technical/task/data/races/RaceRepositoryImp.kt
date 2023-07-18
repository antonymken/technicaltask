package com.technical.task.data.races

import com.technical.task.data.RetrofitClient
import com.technical.task.data.races.di.RaceApiUrl
import com.technical.task.data.races.mapper.NextToGoRaceDataListToDomainList
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.repository.RaceRepository
import okhttp3.OkHttpClient
import javax.inject.Inject

class RaceRepositoryImp @Inject constructor(
    @RaceApiUrl private val url: String
) : RaceRepository, RetrofitClient() {

    override val baseUrl: String = url
    private val raceApi = createApiClient<RaceApi>()

    override fun getNextToGo(itemCount: Int): List<NextToGoRaceDomainModel> {
        return NextToGoRaceDataListToDomainList.transformList(
            raceApi.getNextToGo("method=nextraces&count={$itemCount}").execute().body()?.data?.races?.values
        ).toList()
    }
}