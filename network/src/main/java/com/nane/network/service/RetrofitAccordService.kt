package com.nane.network.service

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitAccordService {

    @GET("api/accord")
    suspend fun getAllAccords(): Response<AccordApi.Accords>

    @GET("api/accord/{id}/exhibition")
    suspend fun getAccordDetail(
        @Path(value = "id") id: Int,
    ): Response<AccordApi.AccordDetail>
}