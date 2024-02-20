package com.nane.network.service

import com.nane.network.api.search.SearchApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitSearchService {

    @GET("api/perfume")
    suspend fun getPerfumes(
        @Query(value = "name") query: String,
        @Query(value = "offset") loadPage: Int,
        @Query(value = "limit") loadSize: Int
    ): Response<SearchApi.Perfumes>
}