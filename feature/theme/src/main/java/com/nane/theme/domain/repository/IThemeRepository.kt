package com.nane.theme.domain.repository

import com.nane.base.data.DataResult
import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import kotlinx.coroutines.flow.Flow

interface IThemeRepository {
    suspend fun getPopularAccords(): Flow<DataResult<AccordApi.Accords?>>
    suspend fun getAllAccords(): Flow<DataResult<AccordApi.Accords?>>

    suspend fun getPopularBrands(): Flow<DataResult<BrandApi.Brands?>>
    suspend fun getAllBrands(): Flow<DataResult<BrandApi.Brands?>>
}