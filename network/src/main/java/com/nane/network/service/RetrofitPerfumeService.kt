package com.nane.network.service

import com.nane.network.api.home.HomeApi
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by iseungjun on 2023/09/11
 */
interface RetrofitPerfumeService {

    @GET("api/perfume/main")
    suspend fun getHomeInfo(): Response<HomeApi.Response>

}