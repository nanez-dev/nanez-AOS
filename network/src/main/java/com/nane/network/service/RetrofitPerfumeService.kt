package com.nane.network.service

import com.nane.network.api.detail.PerfumeDetailApi
import com.nane.network.api.home.HomeApi
import com.nane.network.api.storage.StorageApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/09/11
 */
interface RetrofitPerfumeService {

    @GET("api/perfume/main")
    suspend fun getHomeInfo(): Response<HomeApi.Response>

    @GET("api/perfume/my-list")
    suspend fun getMyList(@Query("btn") type: String?): Response<List<StorageApi.Response>>

    @GET("api/perfume/{perfume_id}")
    suspend fun getPerfumeDetail(@Path("perfume_id") perfumeId: Int): Response<PerfumeDetailApi.Response>

    @PATCH("api/perfume/{perfume_id}?btn=wish")
    suspend fun patchPerfumeWish(@Path("perfume_id") perfumeId: Int): Response<Boolean>

    @PATCH("api/perfume/{perfume_id}?btn=having")
    suspend fun patchPerfumeHaving(@Path("perfume_id") perfumeId: Int): Response<Boolean>
}