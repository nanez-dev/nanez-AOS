package com.nane.network.service

import com.nane.network.api.theme.BrandApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitBrandService {

    @GET("api/brand/exhibition")
    suspend fun getBrands(): Response<BrandApi.Brands>

    @GET("api/brand/{id}/exhibition")
    suspend fun getBrandDetail(
        @Path(value = "id") brandId: Int
    ): Response<BrandApi.BrandDetail>
}