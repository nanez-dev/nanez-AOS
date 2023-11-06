package com.nane.network.service

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitAccordService {

    @GET("api/accord")
    suspend fun getAllAccord(): Response<String>

}