package com.nane.theme.data.source.remote

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import com.nane.network.service.RetrofitAccordService
import com.nane.network.service.RetrofitBrandService
import com.nane.theme.data.source.IThemeRemoteSource
import retrofit2.Response
import javax.inject.Inject

class ThemeRemoteSourceImpl @Inject constructor(
    private val accordService: RetrofitAccordService,
    private val brandService: RetrofitBrandService
): IThemeRemoteSource {

    override suspend fun getPopularAccords(): Response<AccordApi.Accords> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAccords(): Response<AccordApi.Accords> {
        return accordService.getAllAccords()
    }

    override suspend fun getAllBrands(): Response<BrandApi.Brands> {
        return brandService.getAllBrands()
    }

    override suspend fun getPopularBrands(): Response<BrandApi.Brands> {
        return brandService.getPopularBrands()
    }

    override suspend fun getBrandDetail(brandId: Int, limit: Int): Response<BrandApi.BrandDetail> {
        return brandService.getBrandDetail(brandId = brandId, limit = limit)
    }
}