package com.technical.task.data.races

import com.technical.task.data.RetrofitClient
import com.technical.task.data.races.di.RaceApiUrl
import com.technical.task.data.races.mapper.NextToGoRaceDataListToDomainList
import com.technical.task.domain.error.ApiException
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.domain.races.repository.RaceRepository
import javax.inject.Inject

class RaceRepositoryImp @Inject constructor(
    @RaceApiUrl private val url: String
) : RaceRepository, RetrofitClient() {

    override val baseUrl: String = url
    private val raceApi = createApiClient<RaceApi>()

    override fun getNextToGo(itemCount: Int): List<NextToGoRaceDomainModel> {
        val result = raceApi.getNextToGo(METHOD, itemCount).execute()
        if (result.errorBody() != null){
            throw ApiException("server error")
        }
        return NextToGoRaceDataListToDomainList.transformList(
            result.body()?.data?.races?.values
        ).toList()
    }
}

const val METHOD = "nextraces"