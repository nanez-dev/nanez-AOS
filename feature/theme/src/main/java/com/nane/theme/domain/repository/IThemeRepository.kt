package com.nane.theme.domain.repository

import com.nane.base.data.DataResult
import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.domain.data.AccordsDomainDTO
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.BrandsDomainDTO
import kotlinx.coroutines.flow.Flow

interface IThemeRepository {
    suspend fun getAccords(): Flow<DataResult<AccordsDomainDTO>>
    suspend fun getBrands(): Flow<DataResult<BrandsDomainDTO>>

    suspend fun getAccordDetail(id: Int): Flow<DataResult<AccordDetailDomainDTO>>
    suspend fun getBrandDetail(brandId: Int): Flow<DataResult<BrandDetailDomainDTO>>
}