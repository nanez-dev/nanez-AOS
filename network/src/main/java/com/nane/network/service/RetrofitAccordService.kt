package com.nane.network.service

import com.nane.network.api.theme.AccordApi
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitAccordService {

    @GET("api/accord")
    suspend fun getAllAccords(): Response<AccordApi.Accords>

}