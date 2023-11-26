package com.nane.network.service

import com.nane.network.api.theme.BrandApi
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitBrandService {

    @GET("api/brand/popular")
    suspend fun getPopularBrands(): Response<BrandApi.Brands>

    @GET("api/brand")
    suspend fun getAllBrands(): Response<BrandApi.Brands>

}