package com.nane.theme.data.source

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import retrofit2.Response

interface IThemeRemoteSource {
   suspend fun getAccords(): Response<AccordApi.Accords>
   suspend fun getBrands(): Response<BrandApi.Brands>

   suspend fun getAccordDetail(id: Int): Response<AccordApi.AccordDetail>
   suspend fun getBrandDetail(brandId: Int): Response<BrandApi.BrandDetail>
}