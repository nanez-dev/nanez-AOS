package com.nane.theme.data.source

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import retrofit2.Response

interface IThemeRemoteSource {
   suspend fun getPopularAccords(): Response<AccordApi.Accords>
   suspend fun getAllAccords(): Response<AccordApi.Accords>

   suspend fun getPopularBrands(): Response<BrandApi.Brands>
   suspend fun getAllBrands(): Response<BrandApi.Brands>

}