package com.nane.network.service

import com.nane.network.api.home.HomeApi
import com.nane.network.api.storage.StorageApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/09/11
 */
interface RetrofitPerfumeService {

    @GET("api/perfume/main")
    suspend fun getHomeInfo(): Response<HomeApi.Response>

    // 코루틴 -> suspend fun이라고 명시를 해주어야 함
    @GET("api/perfume/my-list")
    suspend fun getMyList(@Query("btn") type: String?): Response<String>

}