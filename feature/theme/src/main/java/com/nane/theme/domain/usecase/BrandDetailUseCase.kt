package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.data.mapper.BrandDataMapper
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.repository.IThemeRepository
import com.nane.theme.presentation.data.BrandDetailViewData
import com.nane.theme.presentation.mapper.BrandDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandDetailUseCase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: BrandDomainMapper
) {

    suspend fun getBrandDetail(brandId: Int): Flow<DomainResult<BrandDetailViewData>> = flow {
        repository.getBrandDetail(brandId = brandId).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toDetailViewData(response.data)))
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