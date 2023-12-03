package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.data.mapper.BrandDetailDataMapper
import com.nane.theme.data.mapper.BrandsDataMapper
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.mapper.BrandDetailDomainMapper
import com.nane.theme.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandDetailUsecase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: BrandDetailDataMapper
) {

    suspend fun getBrandDetail(brandId: Int, limit: Int): Flow<DomainResult<BrandDetailDomainDTO>> = flow {
        repository.getBrandDetail(brandId = brandId, limit = limit).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toDTO(response.data)))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg, response.code))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }
}