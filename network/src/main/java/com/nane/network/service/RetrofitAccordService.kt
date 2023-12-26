package com.nane.network.service

import com.nane.network.api.theme.AccordApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitAccordService {

    @GET("api/accord/exhibition")
    suspend fun getAccords(): Response<AccordApi.Accords>

    @GET("api/accord/{id}/exhibition")
    suspend fun getAccordDetail(
        @Path(value = "id") id: Int,
    ): Response<AccordApi.AccordDetail>
}