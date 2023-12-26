package com.nane.theme.domain.repository

import com.nane.base.data.DataResult
import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import kotlinx.coroutines.flow.Flow

interface IThemeRepository {
    suspend fun getAccords(): Flow<DataResult<AccordApi.Accords?>>
    suspend fun getBrands(): Flow<DataResult<BrandApi.Brands?>>

    suspend fun getAccordDetail(id: Int): Flow<DataResult<AccordApi.AccordDetail?>>
    suspend fun getBrandDetail(brandId: Int): Flow<DataResult<BrandApi.BrandDetail?>>
}