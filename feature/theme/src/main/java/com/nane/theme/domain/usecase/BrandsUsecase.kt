package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.data.mapper.BrandsDataMapper
import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandsUsecase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: BrandsDataMapper
) {

    suspend fun getAllBrands(): Flow<DomainResult<BrandsDomainDTO>> = flow {
        repository.getBrands().collect { response ->
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