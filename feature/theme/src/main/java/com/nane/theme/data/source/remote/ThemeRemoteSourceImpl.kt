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

    override suspend fun getAccords(): Response<AccordApi.Accords> {
        return accordService.getAccords()
    }

    override suspend fun getBrands(): Response<BrandApi.Brands> {
        return brandService.getBrands()
    }

    override suspend fun getAccordDetail(id: Int): Response<AccordApi.AccordDetail> {
        return accordService.getAccordDetail(id = id)
    }

    override suspend fun getBrandDetail(brandId: Int): Response<BrandApi.BrandDetail> {
        return brandService.getBrandDetail(brandId = brandId)
    }
}