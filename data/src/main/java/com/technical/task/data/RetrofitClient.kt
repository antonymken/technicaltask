package com.technical.task.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


abstract class RetrofitClient {

    abstract val baseUrl: String

    protected inline fun <reified T> createApiClient(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(T::class.java)
    }

}